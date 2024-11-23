package com.wora.citronix.models.DTOs.fieldDtos;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record EmbeddedFieldsDto(
        @NotNull @Positive Long id,
        @NotBlank String name,
        @NotNull @Positive @DecimalMin(value = "0.1", message = "The min surface value of field must be 0.1 Hectar")
        Double surface
) {
}
