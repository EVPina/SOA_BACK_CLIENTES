package com.soa.soaclientes.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.soa.soaclientes.dto.ClienteRequest;
import com.soa.soaclientes.dto.ClienteResponse;
import com.soa.soaclientes.dto.CumpleanosRequest;
import com.soa.soaclientes.dto.LoginRequest;
import com.soa.soaclientes.dto.LoginResponse;
import com.soa.soaclientes.service.ClienteService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/clientes", produces = MediaType.APPLICATION_XML_VALUE)
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping(consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<ClienteResponse> crear(@Valid @RequestBody ClienteRequest dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.crear(dto));
    }

    @GetMapping
    public List<ClienteResponse> listar() {
        return clienteService.listar();
    }

    @GetMapping("/{id}")
    public ClienteResponse obtener(@PathVariable UUID id) {
        return clienteService.obtenerPorId(id);
    }

    @GetMapping("/telefono/{telefono}")
    public ClienteResponse obtenerPorTelefono(@PathVariable String telefono) {
        return clienteService.obtenerPorTelefono(telefono);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_XML_VALUE)
    public ClienteResponse actualizar(@PathVariable UUID id, @Valid @RequestBody ClienteRequest dto) {
        return clienteService.actualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> desactivar(@PathVariable UUID id) {
        clienteService.desactivar(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(value = "/{id}/cumpleanos", consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<Void> registrarCumpleanos(@PathVariable UUID id,
                                                     @Valid @RequestBody CumpleanosRequest dto) {
        clienteService.registrarCumpleanos(id, dto);
        return ResponseEntity.ok().build();
    }

    // controller/ClienteController.java
// Agrega estos métodos:

    // Endpoint de LOGIN
    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
        LoginResponse response = clienteService.login(request);
        if (response.success()) {
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    }

    // Endpoint de BÚSQUEDA por email O teléfono
    @GetMapping("/buscar")
    public ClienteResponse buscarPorTelefonoOEmail(@RequestParam String valor) {
        // Este método requiere implementar en service
        return clienteService.buscarPorTelefonoOEmail(valor);
    }
}
