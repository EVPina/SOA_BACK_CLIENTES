package com.soa.soaclientes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.soa.soaclientes.dto.ClienteRequest;
import com.soa.soaclientes.dto.ClienteResponse;
import com.soa.soaclientes.dto.CumpleanosRequest;
import com.soa.soaclientes.entity.Cliente;
import com.soa.soaclientes.entity.Cumpleanos;
import com.soa.soaclientes.exception.BusinessException;
import com.soa.soaclientes.exception.ResourceNotFoundException;
import com.soa.soaclientes.repository.ClienteRepository;
import com.soa.soaclientes.repository.CumpleanosRepository;

import java.util.List;
import java.util.UUID;

@Service
public class ClienteService {

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private CumpleanosRepository cumpleanosRepository;

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

    @Transactional
    public void registrarCumpleanos(UUID id, CumpleanosRequest dto) {
        Cliente cliente = findEntityById(id);
        Cumpleanos cumpleanos = cumpleanosRepository.findById(id).orElse(new Cumpleanos());
        cumpleanos.setCliente(cliente);
        cumpleanos.setFechaNacimiento(dto.fechaNacimiento());
        cumpleanos.setEnviarPromocion(dto.enviarPromocion() != null ? dto.enviarPromocion() : true);
        cumpleanosRepository.save(cumpleanos);
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
}
