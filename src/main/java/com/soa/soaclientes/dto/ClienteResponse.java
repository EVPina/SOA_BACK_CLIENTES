package com.soa.soaclientes.dto;

import java.time.LocalDate;
import java.util.UUID;

import io.swagger.v3.oas.annotations.media.Schema;

public record ClienteResponse(
    @Schema(description = "ID del cliente")
    UUID id,
    @Schema(description = "Nombre del cliente")
    String nombre,
    @Schema(description = "Apellido del cliente")
    String apellido,
    @Schema(description = "Teléfono del cliente")
    String telefono,
    @Schema(description = "Email del cliente")
    String email,
    @Schema(description = "Fecha de registro del cliente")
    LocalDate fechaRegistro,
    @Schema(description = "Fecha de la última visita del cliente")
    LocalDate ultimaVisita,
    @Schema(description = "Número de veces que el cliente ha visitado")
    Integer vecesVisitado,
    @Schema(description = "Indica si el cliente está activo")
    Boolean activo
) {}
