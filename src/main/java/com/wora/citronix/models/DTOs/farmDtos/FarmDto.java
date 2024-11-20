package com.wora.citronix.models.DTOs.farmDtos;

import com.wora.citronix.models.DTOs.fieldDtos.EmbeddedFieldsDto;
import com.wora.citronix.models.DTOs.harvestDtos.EmbeddedHarvestDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;
import java.util.List;

public record FarmDto(
        @NotNull @Positive Long id,
        @NotBlank String name,
        @NotBlank String localization,
        @NotNull @Positive Double surface,
        @NotNull LocalDate creationDate,
        List<EmbeddedFieldsDto> fields
) {
}
