package com.soa.soaclientes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.soa.soaclientes.dto.PromocionResponse;
import com.soa.soaclientes.service.PromocionService;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/promociones", produces = MediaType.APPLICATION_XML_VALUE)
public class PromocionController {

    @Autowired
    private PromocionService promocionService;

    @GetMapping
    public List<PromocionResponse> listar() {
        return promocionService.listarActivas();
    }

    @GetMapping("/{id}")
    public PromocionResponse obtener(@PathVariable Long id) {
        return promocionService.obtenerPorId(id);
    }
}
