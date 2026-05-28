package com.soa.soaclientes.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import java.math.BigDecimal;
import java.time.LocalDate;

@JacksonXmlRootElement(localName = "promocion")
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
