package com.soa.soaclientes.dto;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public record CumpleanosRequest(
    @NotNull LocalDate fechaNacimiento,
    Boolean enviarPromocion
) {}
