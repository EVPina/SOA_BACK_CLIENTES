// dto/LoginResponse.java
package com.soa.soaclientes.dto;

import java.util.UUID;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import io.swagger.v3.oas.annotations.media.Schema;

@JacksonXmlRootElement(localName = "login-response")
@Schema(description = "Respuesta del inicio de sesión")
public record LoginResponse(
     @Schema(description = "Indica si el login fue exitoso") 
    boolean success,
    @Schema(description = "Mensaje de respuesta") 
    String mensaje,
    @Schema(description = "ID del cliente") 
    UUID id,
    @Schema(description = "Nombre del cliente") 
    String nombre,
    @Schema(description = "Apellido del cliente") 
    String apellido,
    @Schema(description = "Email del cliente") 
    String email,
    @Schema(description = "Teléfono del cliente") 
    String telefono
) {}