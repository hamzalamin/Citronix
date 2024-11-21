package com.wora.citronix.models.DTOs.harvestDetailDtos;

import com.wora.citronix.models.DTOs.harvestDtos.EmbeddedHarvestDto;
import com.wora.citronix.models.DTOs.treeDtos.EmbeddedTreeDto;
import com.wora.citronix.models.entities.embeddables.HarvestDetailsId;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record HarvestDetailsDto(
        HarvestDetailsId id,
        @Positive @NotNull Integer quantity,
        EmbeddedHarvestDto harvest,
        EmbeddedTreeDto tree
) {
}
