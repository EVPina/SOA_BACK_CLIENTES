package com.soa.soaclientes.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.soa.soaclientes.dto.CanjeRequest;
import com.soa.soaclientes.dto.CanjeResponse;
import com.soa.soaclientes.dto.ClienteResponse;
import com.soa.soaclientes.dto.HistorialPuntosResponse;
import com.soa.soaclientes.dto.PuntosRequest;
import com.soa.soaclientes.service.PuntosService;

import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/v1/clientes/{clienteId}", produces = MediaType.APPLICATION_XML_VALUE)
@Tag(name = "Puntos", description = "API para gestión de puntos")
public class PuntosController {

    @Autowired
    private PuntosService puntosService;

    @PostMapping(value = "/puntos/ganar", consumes = MediaType.APPLICATION_XML_VALUE)
    public ClienteResponse ganarPuntos(@PathVariable UUID clienteId,
                                       @Valid @RequestBody PuntosRequest dto) {
        return puntosService.ganarPuntos(clienteId, dto);
    }

    @GetMapping("/puntos/historial")
    public List<HistorialPuntosResponse> historial(@PathVariable UUID clienteId) {
        return puntosService.obtenerHistorial(clienteId);
    }

    @PostMapping(value = "/canjes", consumes = MediaType.APPLICATION_XML_VALUE)
    public CanjeResponse canjear(@PathVariable UUID clienteId,
                                 @Valid @RequestBody CanjeRequest dto) {
        return puntosService.canjearPromocion(clienteId, dto);
    }

    @GetMapping("/canjes")
    public List<CanjeResponse> listarCanjes(@PathVariable UUID clienteId) {
        return puntosService.obtenerCanjes(clienteId);
    }
}
