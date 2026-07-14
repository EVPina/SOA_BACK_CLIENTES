package com.soa.soaclientes.dto;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;

public record CumpleanosRequest(
    @NotNull @Schema(description = "Fecha de nacimiento del cliente") 
    LocalDate fechaNacimiento,
    @NotNull @Schema(description = "Indica si se debe enviar una promoción en el cumpleaños")
     Boolean enviarPromocion
) {}
