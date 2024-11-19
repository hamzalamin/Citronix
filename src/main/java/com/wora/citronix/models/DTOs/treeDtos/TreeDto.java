package com.wora.citronix.models.DTOs.treeDtos;

import com.wora.citronix.models.DTOs.fieldDtos.EmbeddedFieldsDto;
import com.wora.citronix.models.DTOs.harvestDetailDtos.EmbeddedHarvestDetailDto;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;
import java.util.List;

public record TreeDto(
        @NotNull @Positive Long id,
        @NotNull LocalDate plantingDate,
        @NotNull EmbeddedFieldsDto field,
        @NotNull List<EmbeddedHarvestDetailDto> harvestDetails
) {
}
