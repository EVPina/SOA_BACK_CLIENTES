package com.soa.soaclientes.dto;

import jakarta.validation.constraints.NotNull;

public record CanjeRequest(@NotNull Long promocionId) {}
