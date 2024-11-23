package com.wora.citronix.models.DTOs.fieldDtos;

import jakarta.validation.constraints.*;

public record CreateFieldDto(
        @NotBlank String name,
        @NotNull @Positive @DecimalMin(value = "0.1", message = "The min surface value of field must be 0.1 Hectar")
        Double surface,
        @NotNull @Positive Long farmId
) {
}
