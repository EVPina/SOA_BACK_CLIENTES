package com.soa.soaclientes.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;

public record PromocionResponse(
    @Schema(description = "ID de la promoción")
    Long id,
    @Schema(description = "Nombre de la promoción")
    String nombre,
    @Schema(description = "Descripción de la promoción")
    String descripcion,
    @Schema(description = "Tipo de la promoción")
    String tipo,
    @Schema(description = "Valor de la promoción")
    BigDecimal valor,
    @Schema(description = "Puntos requeridos para la promoción")
    Integer puntosRequeridos,
    @Schema(description = "Fecha de inicio de la promoción")
    LocalDate fechaInicio,
    @Schema(description = "Fecha de finalización de la promoción")
    LocalDate fechaFin
) {}
