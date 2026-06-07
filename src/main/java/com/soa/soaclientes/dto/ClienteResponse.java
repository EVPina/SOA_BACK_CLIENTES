package com.soa.soaclientes.dto;

import java.time.LocalDate;
import java.util.UUID;

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
