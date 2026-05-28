package com.soa.soaclientes.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import java.time.LocalDateTime;
import java.util.UUID;

@JacksonXmlRootElement(localName = "canje")
public record CanjeResponse(
    Long id,
    UUID clienteId,
    Long promocionId,
    String nombrePromocion,
    Integer puntosUsados,
    LocalDateTime fechaCanje,
    Boolean canjeado
) {}
