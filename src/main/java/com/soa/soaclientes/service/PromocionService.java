package com.soa.soaclientes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.soa.soaclientes.dto.PromocionResponse;
import com.soa.soaclientes.entity.Promocion;
import com.soa.soaclientes.exception.ResourceNotFoundException;
import com.soa.soaclientes.repository.PromocionRepository;

import java.time.LocalDate;
import java.util.List;

@Service
public class PromocionService {

    @Autowired
    private PromocionRepository promocionRepository;

    @Transactional(readOnly = true)
    public List<PromocionResponse> listarActivas() {
        return promocionRepository.findActivas(LocalDate.now()).stream().map(this::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public PromocionResponse obtenerPorId(Long id) {
        return toResponse(promocionRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Promoción no encontrada con id " + id)));
    }

    private PromocionResponse toResponse(Promocion p) {
        return new PromocionResponse(p.getId(), p.getNombre(), p.getDescripcion(), p.getTipo(),
            p.getValor(), p.getPuntosRequeridos(), p.getFechaInicio(), p.getFechaFin());
    }
}
