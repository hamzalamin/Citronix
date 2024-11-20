package com.wora.citronix.models.DTOs.harvestDtos;

import com.wora.citronix.models.enumes.Season;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record UpdateHarvestDto(
        @NotNull LocalDate creationDate,
        @NotNull Season season
) {
}
