package com.wora.citronix.models.DTOs.fieldDtos;

import com.wora.citronix.models.DTOs.farmDtos.EmbeddedFarmDto;
import com.wora.citronix.models.DTOs.treeDtos.EmbeddedTreeDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.List;

public record FieldDto(
        @NotNull @Positive Long id,
        @NotBlank String name,
        @NotNull @Positive Double surface,
        EmbeddedFarmDto farm,
        List<EmbeddedTreeDto> trees
) {
}
