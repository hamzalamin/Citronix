package com.wora.citronix.models.DTOs.treeDtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record TreeDto(
        @NotNull @Positive Long id

) {
}
