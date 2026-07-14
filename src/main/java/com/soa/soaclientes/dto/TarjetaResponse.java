package com.soa.soaclientes.dto;

import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;

public record TarjetaResponse(
    @Schema(description = "ID de la tarjeta")
    Long id,
    @Schema(description = "Código de la tarjeta")
    String codigoTarjeta,
    @Schema(description = "Fecha de emisión de la tarjeta")
    LocalDate fechaEmision,
    @Schema(description = "Fecha de vencimiento de la tarjeta")
    LocalDate fechaVencimiento,
    @Schema(description = "Indica si la tarjeta está activa")
    Boolean activa
) {}
