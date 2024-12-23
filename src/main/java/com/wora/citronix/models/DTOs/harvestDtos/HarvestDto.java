package com.wora.citronix.models.DTOs.harvestDtos;

import com.wora.citronix.models.DTOs.harvestDetailDtos.EmbeddedHarvestDetailDto;
import com.wora.citronix.models.DTOs.saleDtos.EmbeddedSaleDto;
import com.wora.citronix.models.enumes.Season;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;
import java.util.List;

public record HarvestDto(
        @NotNull @Positive Long id,
        @NotNull LocalDate creationDate,
        @NotNull Season season,
        List<EmbeddedHarvestDetailDto> harvestDetails,
        List<EmbeddedSaleDto> sales
) {
}
