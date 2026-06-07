// dto/LoginResponse.java
package com.soa.soaclientes.dto;

import java.util.UUID;

public record LoginResponse(
    boolean success,
    String mensaje,
    UUID id,
    String nombre,
    String apellido,
    String email,
    String telefono
) {}