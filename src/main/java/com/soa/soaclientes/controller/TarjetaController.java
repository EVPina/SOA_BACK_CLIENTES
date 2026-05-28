package com.soa.soaclientes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.soa.soaclientes.dto.TarjetaResponse;
import com.soa.soaclientes.service.TarjetaService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/clientes/{clienteId}/tarjetas", produces = MediaType.APPLICATION_XML_VALUE)
public class TarjetaController {

    @Autowired
    private TarjetaService tarjetaService;

    @PostMapping
    public ResponseEntity<TarjetaResponse> emitir(@PathVariable UUID clienteId) {
        return ResponseEntity.status(HttpStatus.CREATED).body(tarjetaService.emitirTarjeta(clienteId));
    }

    @GetMapping
    public List<TarjetaResponse> listar(@PathVariable UUID clienteId) {
        return tarjetaService.listarPorCliente(clienteId);
    }
}
