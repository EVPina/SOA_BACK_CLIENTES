package com.soa.soaclientes.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record CanjeResponse(
    Long id,
    UUID clienteId,
    Long promocionId,
    String nombrePromocion,
    Integer puntosUsados,
    LocalDateTime fechaCanje,
    Boolean canjeado
) {}
