package com.soa.soaclientes.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import jakarta.validation.constraints.NotNull;

@JacksonXmlRootElement(localName = "canje-request")
public record CanjeRequest(@NotNull Long promocionId) {}
