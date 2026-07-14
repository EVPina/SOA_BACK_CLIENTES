package com.soa.soaclientes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.soa.soaclientes.dto.PromocionResponse;
import com.soa.soaclientes.service.PromocionService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/promociones", produces = MediaType.APPLICATION_XML_VALUE)
public class PromocionController {

    @Autowired
    private PromocionService promocionService;

      @Operation(
        summary = "Se lista promociones activas",
        description = "Retorna la lista de promociones activas"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Promociones encontradas exitosamente"),
        @ApiResponse(responseCode = "404", description = "Promociones no encontradas")
    })
    @GetMapping
    public List<PromocionResponse> listar() {
        return promocionService.listarActivas();
    }


    @Operation(
        summary = "Se obtiene una promocion por su ID",
        description = "Retorna la información de una promocion específica por su ID"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Promocion encontrada exitosamente"),
        @ApiResponse(responseCode = "404", description = "Promocion no encontrada")
    })
    @GetMapping("/{id}")
    public PromocionResponse obtener(@PathVariable Long id) {
        return promocionService.obtenerPorId(id);
    }
}
