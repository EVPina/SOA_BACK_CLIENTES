package com.soa.soaclientes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.soa.soaclientes.dto.ClienteRequest;
import com.soa.soaclientes.dto.ClienteResponse;
import com.soa.soaclientes.dto.CumpleanosRequest;
import com.soa.soaclientes.dto.GoogleLoginRequest;
import com.soa.soaclientes.dto.LoginRequest;
import com.soa.soaclientes.dto.LoginResponse;
import com.soa.soaclientes.entity.Cliente;
import com.soa.soaclientes.exception.BusinessException;
import com.soa.soaclientes.exception.ResourceNotFoundException;
import com.soa.soaclientes.repository.ClienteRepository;

import java.security.GeneralSecurityException;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
public class ClienteService {

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Value("${google.client-id}")
    private String googleClientId;

    private GoogleIdTokenVerifier googleIdTokenVerifier;

    @Autowired
    private ClienteRepository clienteRepository;

    private GoogleIdTokenVerifier googleVerifier() {
        if (googleIdTokenVerifier == null) {
            try {
                googleIdTokenVerifier = new GoogleIdTokenVerifier.Builder(
                        GoogleNetHttpTransport.newTrustedTransport(), GsonFactory.getDefaultInstance())
                        .setAudience(Collections.singletonList(googleClientId))
                        .build();
            } catch (GeneralSecurityException | IOException e) {
                throw new RuntimeException("No se pudo inicializar el verificador de Google", e);
            }
        }
        return googleIdTokenVerifier;
    }

    @Transactional
    public ClienteResponse crear(ClienteRequest dto) {
        if (clienteRepository.existsByTelefono(dto.telefono())) {
            throw new BusinessException("Ya existe un cliente con el teléfono " + dto.telefono());
        }
        if (clienteRepository.existsByEmail(dto.email())) {
            throw new BusinessException("Ya existe un cliente con el email " + dto.email());
        }
        Cliente cliente = new Cliente();
        cliente.setNombre(dto.nombre());
        cliente.setApellido(dto.apellido());
        cliente.setTelefono(dto.telefono());
        cliente.setEmail(dto.email());
        cliente.setPasswordHash(passwordEncoder.encode(dto.password()));
        return toResponse(clienteRepository.save(cliente));
    }

    @Transactional
    public ClienteResponse actualizar(UUID id, ClienteRequest dto) {
        Cliente cliente = findEntityById(id);
        if (!cliente.getTelefono().equals(dto.telefono()) && clienteRepository.existsByTelefono(dto.telefono())) {
            throw new BusinessException("Ya existe un cliente con el teléfono " + dto.telefono());
        }
        if (!cliente.getEmail().equals(dto.email()) && clienteRepository.existsByEmail(dto.email())) {
            throw new BusinessException("Ya existe un cliente con el email " + dto.email());
        }
        cliente.setNombre(dto.nombre());
        cliente.setApellido(dto.apellido());
        cliente.setTelefono(dto.telefono());
        cliente.setEmail(dto.email());
        cliente.setPasswordHash(passwordEncoder.encode(dto.password()));
        return toResponse(clienteRepository.save(cliente));
    }

    @Transactional(readOnly = true)
    public ClienteResponse obtenerPorId(UUID id) {
        return toResponse(findEntityById(id));
    }

    @Transactional(readOnly = true)
    public ClienteResponse obtenerPorTelefono(String telefono) {
        return toResponse(clienteRepository.findByTelefono(telefono)
            .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado con teléfono " + telefono)));
    }

    @Transactional(readOnly = true)
    public List<ClienteResponse> listar() {
        return clienteRepository.findAll().stream().map(this::toResponse).toList();
    }

    @Transactional
    public void desactivar(UUID id) {
        Cliente cliente = findEntityById(id);
        cliente.setActivo(false);
        clienteRepository.save(cliente);
    }


    public Cliente findEntityById(UUID id) {
        return clienteRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado con id " + id));
    }

    ClienteResponse toResponse(Cliente c) {
        return new ClienteResponse(
            c.getId(), c.getNombre(), c.getApellido(), c.getTelefono(),
            c.getEmail(), c.getFechaRegistro(), c.getUltimaVisita(),
            c.getVecesVisitado(), c.getActivo()
        );
    }

    @Transactional(readOnly = true)
    public LoginResponse login(LoginRequest request) {
        String identificador = request.identificador();
        String password = request.password();
        
        // Buscar por email o teléfono
        Cliente cliente = clienteRepository.findByEmail(identificador)
            .orElseGet(() -> clienteRepository.findByTelefono(identificador)
                .orElse(null));
        
        if (cliente == null) {
            return new LoginResponse(false, "Credenciales incorrectas", null, null, null, null, null);
        }
        
        if (!cliente.getActivo()) {
            return new LoginResponse(false, "Cuenta desactivada", null, null, null, null, null);
        }
        
        // Verificar password
        if (!passwordEncoder.matches(password, cliente.getPasswordHash())) {
            return new LoginResponse(false, "Credenciales incorrectas", null, null, null, null, null);
        }
        
        return new LoginResponse(
            true,
            "Login exitoso",
            cliente.getId(),
            cliente.getNombre(),
            cliente.getApellido(),
            cliente.getEmail(),
            cliente.getTelefono()
        );
    }

    @Transactional
    public LoginResponse loginConGoogle(GoogleLoginRequest request) {
        GoogleIdToken idToken;
        try {
            idToken = googleVerifier().verify(request.idToken());
        } catch (GeneralSecurityException | IOException | IllegalArgumentException e) {
            return new LoginResponse(false, "Token de Google inválido", null, null, null, null, null);
        }

        if (idToken == null) {
            return new LoginResponse(false, "Token de Google inválido", null, null, null, null, null);
        }

        GoogleIdToken.Payload payload = idToken.getPayload();
        if (!Boolean.TRUE.equals(payload.getEmailVerified())) {
            return new LoginResponse(false, "El correo de Google no está verificado", null, null, null, null, null);
        }

        String email = payload.getEmail();
        String nombre = (String) payload.get("given_name");
        String apellido = (String) payload.get("family_name");

        Cliente cliente = clienteRepository.findByEmail(email).orElseGet(() -> {
            Cliente nuevo = new Cliente();
            nuevo.setNombre(nombre != null ? nombre : "Cliente");
            nuevo.setApellido(apellido != null ? apellido : "");
            nuevo.setEmail(email);
            return clienteRepository.save(nuevo);
        });

        if (!cliente.getActivo()) {
            return new LoginResponse(false, "Cuenta desactivada", null, null, null, null, null);
        }

        return new LoginResponse(
            true,
            "Login exitoso",
            cliente.getId(),
            cliente.getNombre(),
            cliente.getApellido(),
            cliente.getEmail(),
            cliente.getTelefono()
        );
    }

    @Transactional(readOnly = true)
    public ClienteResponse buscarPorTelefonoOEmail(String valor) {
        Cliente cliente = clienteRepository.buscarPorTelefonoOEmail(valor)
            .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado con: " + valor));
        return toResponse(cliente);
    }
}
