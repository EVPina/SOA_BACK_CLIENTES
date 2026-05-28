package com.soa.soaclientes.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import java.time.LocalDateTime;

@JacksonXmlRootElement(localName = "movimiento")
public record HistorialPuntosResponse(
    Long id,
    String tipo,
    Integer puntos,
    String concepto,
    Integer pedidoId,
    LocalDateTime fecha
) {}
