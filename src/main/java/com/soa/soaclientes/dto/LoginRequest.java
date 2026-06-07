// dto/LoginRequest.java
package com.soa.soaclientes.dto;

import jakarta.validation.constraints.NotBlank;

public record LoginRequest(
    @NotBlank(message = "El email o teléfono es obligatorio") 
    String identificador,
    
    @NotBlank(message = "La contraseña es obligatoria") 
    String password
) {}