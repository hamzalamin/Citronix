package com.wora.citronix.models.DTOs.harvestDetailDtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record CreateHarvestDetailsDto(
        @Positive @NotNull Long treeId,
        @Positive @NotNull Long harvestId,
        @NotNull @Positive Integer quantity
) {
}
