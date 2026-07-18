// dto/GoogleLoginRequest.java
package com.soa.soaclientes.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "ID token emitido por Google Identity Services")
public record GoogleLoginRequest(

    @Schema(description = "ID token (JWT) devuelto por el botón de Google Sign-In", required = true)
    @NotBlank(message = "El idToken es obligatorio")
    String idToken
) {}
