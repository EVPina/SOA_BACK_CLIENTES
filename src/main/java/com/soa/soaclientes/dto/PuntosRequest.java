package com.soa.soaclientes.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@JacksonXmlRootElement(localName = "puntos-request")
public record PuntosRequest(
    @NotNull @Min(1) Integer puntos,
    @NotBlank String concepto,
    Integer pedidoId
) {}
