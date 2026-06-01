// dto/LoginRequest.java
package com.soa.soaclientes.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import jakarta.validation.constraints.NotBlank;

@JacksonXmlRootElement(localName = "login-request")
public record LoginRequest(
    @NotBlank(message = "El email o teléfono es obligatorio") 
    String identificador,
    
    @NotBlank(message = "La contraseña es obligatoria") 
    String password
) {}