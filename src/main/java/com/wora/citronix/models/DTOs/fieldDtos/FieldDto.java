package com.wora.citronix.models.DTOs.fieldDtos;

import com.wora.citronix.models.DTOs.farmDtos.EmbeddedFarmDto;
import com.wora.citronix.models.DTOs.harvestDtos.EmbeddedHarvestDto;
import com.wora.citronix.models.DTOs.treeDtos.EmbeddedTreeDto;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.List;

public record FieldDto(
        @NotNull @Positive Long id,
        @NotBlank String name,
        @NotNull @Positive @DecimalMin(value = "0.1", message = "The min surface value of field must be 0.1 Hectar")
        Double surface,
        EmbeddedFarmDto farm,
        List<EmbeddedTreeDto> trees,
        List<EmbeddedHarvestDto> harvests
) {
}
