// dto/LoginResponse.java
package com.soa.soaclientes.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import java.util.UUID;

@JacksonXmlRootElement(localName = "login-response")
public record LoginResponse(
    boolean success,
    String mensaje,
    UUID id,
    String nombre,
    String apellido,
    String email,
    String telefono
) {}