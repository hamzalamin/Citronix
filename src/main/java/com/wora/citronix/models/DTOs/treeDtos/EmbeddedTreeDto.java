package com.wora.citronix.models.DTOs.treeDtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record EmbeddedTreeDto(
        @NotNull @Positive Long id
) {
}
