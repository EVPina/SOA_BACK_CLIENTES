package com.soa.soaclientes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.soa.soaclientes.dto.TarjetaResponse;
import com.soa.soaclientes.service.TarjetaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/v1/clientes/{clienteId}/tarjetas", produces = MediaType.APPLICATION_XML_VALUE)
public class TarjetaController {

    @Autowired
    private TarjetaService tarjetaService;

    @Operation(
        summary = "Listar todas las tarjetas",
        description = "Retorna una emision tarjetas asociadas a un cliente"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista obtenida exitosamente"),
        @ApiResponse(responseCode = "204", description = "No hay tarjetas disponibles")
    })
    @PostMapping
    public ResponseEntity<TarjetaResponse> emitir(@PathVariable UUID clienteId) {
        return ResponseEntity.status(HttpStatus.CREATED).body(tarjetaService.emitirTarjeta(clienteId));
    }


@Operation(
        summary = "Listar todas las tarjetas",
        description = "Retorna una lista de todas las tarjetas asociadas a un cliente"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista obtenida exitosamente"),
        @ApiResponse(responseCode = "204", description = "No hay tarjetas disponibles")
    })
    @GetMapping
    public List<TarjetaResponse> listar(@PathVariable UUID clienteId) {
        return tarjetaService.listarPorCliente(clienteId);
    }
}
