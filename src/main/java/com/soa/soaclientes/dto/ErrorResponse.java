package com.soa.soaclientes.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import io.swagger.v3.oas.annotations.media.Schema;

@JacksonXmlRootElement(localName = "error")
@Schema(description = "Respuesta de error")
public record ErrorResponse(
    @Schema(description = "Timestamp del error") String timestamp,
    @Schema(description = "Mensaje descriptivo del error") String mensaje
) {}