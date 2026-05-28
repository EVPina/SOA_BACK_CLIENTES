package com.soa.soaclientes.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

@JacksonXmlRootElement(localName = "cumpleanos-request")
public record CumpleanosRequest(
    @NotNull LocalDate fechaNacimiento,
    Boolean enviarPromocion
) {}
