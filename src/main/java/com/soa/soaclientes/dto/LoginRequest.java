// dto/LoginRequest.java
package com.soa.soaclientes.dto;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@JacksonXmlRootElement(localName = "login-request")
@Schema(description = "Credenciales de inicio de sesión")
public record LoginRequest(

    @Schema(description = "Email o teléfono del cliente", example = "juan@example.com", required = true)
    @NotBlank(message = "El email o teléfono es obligatorio") 
    String identificador,
    
    @Schema(description = "Email o teléfono del cliente", example = "juan@example.com", required = true)
    @NotBlank(message = "La contraseña es obligatoria") 
    String password
) {}