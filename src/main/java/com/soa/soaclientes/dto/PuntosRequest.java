package com.soa.soaclientes.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PuntosRequest(
    @NotNull @Min(1) Integer puntos,
    @NotBlank String concepto,
    Integer pedidoId
) {}
