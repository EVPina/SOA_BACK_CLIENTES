package com.soa.soaclientes.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import java.time.LocalDate;
import java.util.UUID;

@JacksonXmlRootElement(localName = "cliente")
public record ClienteResponse(
    UUID id,
    String nombre,
    String apellido,
    String telefono,
    String email,
    LocalDate fechaRegistro,
    LocalDate ultimaVisita,
    Integer vecesVisitado,
    Boolean activo
) {}
