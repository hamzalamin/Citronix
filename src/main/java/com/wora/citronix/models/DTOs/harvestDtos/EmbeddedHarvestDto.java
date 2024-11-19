package com.wora.citronix.models.DTOs.harvestDtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record EmbeddedHarvestDto(
        @NotNull @Positive Long id
) {
}
