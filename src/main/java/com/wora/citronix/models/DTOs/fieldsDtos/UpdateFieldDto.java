package com.wora.citronix.models.DTOs.fieldsDtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record UpdateFieldDto(
        @NotBlank String name,
        @NotNull @Positive Double surface,
        @NotNull @Positive Long farmId
) {
}
