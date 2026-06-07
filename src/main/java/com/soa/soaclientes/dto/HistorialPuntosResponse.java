package com.soa.soaclientes.dto;

import java.time.LocalDateTime;

public record HistorialPuntosResponse(
    Long id,
    String tipo,
    Integer puntos,
    String concepto,
    Integer pedidoId,
    LocalDateTime fecha
) {}
