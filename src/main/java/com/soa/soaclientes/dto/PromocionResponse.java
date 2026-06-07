package com.soa.soaclientes.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record PromocionResponse(
    Long id,
    String nombre,
    String descripcion,
    String tipo,
    BigDecimal valor,
    Integer puntosRequeridos,
    LocalDate fechaInicio,
    LocalDate fechaFin
) {}
