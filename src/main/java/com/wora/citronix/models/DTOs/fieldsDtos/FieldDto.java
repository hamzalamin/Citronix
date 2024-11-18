package com.wora.citronix.models.DTOs.fieldsDtos;

import com.wora.citronix.models.DTOs.farmDtos.EmbeddedFarmDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record FieldDto(
        @NotNull @Positive Long id,
        @NotBlank String name,
        @NotNull @Positive Double surface,
        EmbeddedFarmDto farm
) {
}
