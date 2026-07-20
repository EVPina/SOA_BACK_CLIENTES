package com.soa.soaclientes.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.soa.soaclientes.dto.ClienteRequest;
import com.soa.soaclientes.dto.ClienteResponse;
import com.soa.soaclientes.dto.GoogleLoginRequest;
import com.soa.soaclientes.dto.LoginRequest;
import com.soa.soaclientes.dto.LoginResponse;
import com.soa.soaclientes.service.ClienteService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/v1/clientes")
@Tag(name = "Clientes", description = "API para la gestión de clientes del sistema")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping
    public ResponseEntity<ClienteResponse> crear(@Valid @RequestBody ClienteRequest dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.crear(dto));
    }

    @Operation(
        summary = "Listar todos los clientes",
        description = "Retorna una lista de todos los clientes activos en el sistema"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista obtenida exitosamente"),
        @ApiResponse(responseCode = "204", description = "No hay clientes disponibles")
    })
    @GetMapping
    public List<ClienteResponse> listar() {
        return clienteService.listar();
    }

     @Operation(
        summary = "Obtener cliente",
        description = "Retorna la información de un cliente específico por su ID"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Cliente obtenido exitosamente"),
        @ApiResponse(responseCode = "404", description = "Cliente no encontrado")
    })
    @GetMapping("/{id}")
    public ClienteResponse obtener(@PathVariable UUID id) {
        return clienteService.obtenerPorId(id);
    }

     @Operation(
        summary = "Obtener cliente por teléfono",
        description = "Retorna la información de un cliente específico por su número de teléfono"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Cliente obtenido exitosamente"),
        @ApiResponse(responseCode = "404", description = "Cliente no encontrado")
    })
    @GetMapping("/telefono/{telefono}")
    public ClienteResponse obtenerPorTelefono(@PathVariable String telefono) {
        return clienteService.obtenerPorTelefono(telefono);
    }


     @Operation(
        summary = "Actualizar información del cliente",
        description = "Actualiza la información de un cliente específico por su ID"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Cliente actualizado exitosamente"),
        @ApiResponse(responseCode = "404", description = "Cliente no encontrado")
    })
    @PutMapping(value = "/{id}")
    public ClienteResponse actualizar(@PathVariable UUID id, @Valid @RequestBody ClienteRequest dto) {
        return clienteService.actualizar(id, dto);
    }

    @Operation(
        summary = "Eliminar cliente",
        description = "Elimina un cliente específico por su ID"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Cliente eliminado exitosamente"),
        @ApiResponse(responseCode = "404", description = "Cliente no encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> desactivar(@PathVariable UUID id) {
        clienteService.desactivar(id);
        return ResponseEntity.noContent().build();
    }

    // controller/ClienteController.java
// Agrega estos métodos:

    @Operation(
        summary = "Inicio sesion cliente",
        description = "Permite a un cliente iniciar sesión en el sistema"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Cliente autenticado exitosamente"),
        @ApiResponse(responseCode = "401", description = "Credenciales inválidas")
    })
    // Endpoint de LOGIN
    @PostMapping(value = "/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
        LoginResponse response = clienteService.login(request);
        if (response.success()) {
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    }

    @Operation(
        summary = "Inicio sesion cliente con Google",
        description = "Permite a un cliente iniciar sesión (o registrarse automáticamente) usando un ID token de Google Sign-In"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Cliente autenticado exitosamente"),
        @ApiResponse(responseCode = "401", description = "Token de Google inválido")
    })
    @PostMapping(value = "/login-google")
    public ResponseEntity<LoginResponse> loginGoogle(@Valid @RequestBody GoogleLoginRequest request) {
        LoginResponse response = clienteService.loginConGoogle(request);
        if (response.success()) {
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    }

     @Operation(
        summary = "Se busca cliente por email o telefono",
        description = "Permite buscar un cliente por su correo electrónico o número de teléfono"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Cliente encontrado exitosamente"),
        @ApiResponse(responseCode = "404", description = "Cliente no encontrado")
    })
    // Endpoint de BÚSQUEDA por email O teléfono
    @GetMapping("/buscar")
    public ClienteResponse buscarPorTelefonoOEmail(@RequestParam String valor) {
        // Este método requiere implementar en service
        return clienteService.buscarPorTelefonoOEmail(valor);
    }
}
