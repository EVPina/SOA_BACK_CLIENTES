package com.soa.soaclientes.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ClienteRequest(
    @NotBlank @Size(max = 50) String nombre,
    @NotBlank @Size(max = 50) String apellido,
    @NotBlank @Size(max = 15) String telefono,
    @NotBlank @Email @Size(max = 100) String email,
    @NotBlank String password
) {}
