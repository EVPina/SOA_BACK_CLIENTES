package com.soa.soaclientes.dto;

import java.time.LocalDate;

public record TarjetaResponse(
    Long id,
    String codigoTarjeta,
    LocalDate fechaEmision,
    LocalDate fechaVencimiento,
    Boolean activa
) {}
