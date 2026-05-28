package com.soa.soaclientes.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import java.time.LocalDate;

@JacksonXmlRootElement(localName = "tarjeta")
public record TarjetaResponse(
    Long id,
    String codigoTarjeta,
    LocalDate fechaEmision,
    LocalDate fechaVencimiento,
    Boolean activa
) {}
