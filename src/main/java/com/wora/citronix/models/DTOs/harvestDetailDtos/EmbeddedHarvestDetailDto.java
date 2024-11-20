package com.wora.citronix.models.DTOs.harvestDetailDtos;

import com.wora.citronix.models.entities.embeddables.HarvestDetailsId;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record EmbeddedHarvestDetailDto(
        HarvestDetailsId id,
        @Positive @NotNull Integer quantity
) {
}
