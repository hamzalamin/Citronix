package com.wora.citronix.models.DTOs.fieldsDtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record EmbeddedFieldsDto(
        @NotNull @Positive Long id,
        @NotBlank String name,
        @NotNull @Positive Double surface
) {
}
