package com.soa.soaclientes.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@JacksonXmlRootElement(localName = "cliente")
@Schema(description = "Datos para registrar o actualizar un cliente")
public record ClienteRequest(
    @Schema(description = "Nombre del cliente", example = "Juan", required = true)
    @NotBlank @Size(max = 50) String nombre,

    @Schema(description = "Apellido del cliente", example = "Pérez", required = true)
    @NotBlank @Size(max = 50) String apellido,

    @Schema(description = "Teléfono del cliente", example = "1234567890", required = true)
    @NotBlank @Size(max = 15) String telefono,

    @Schema(description = "Email del cliente", example = "juan.perez@example.com", required = true)
    @NotBlank @Email @Size(max = 100) String email,
    
    @Schema(description = "Contraseña del cliente", example = "password123", required = true)
    @NotBlank String password
) {}
