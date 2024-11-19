package com.wora.citronix.models.DTOs.treeDtos;

import com.wora.citronix.models.DTOs.fieldDtos.EmbeddedFieldsDto;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record CreateTreeDto(
        @NotNull LocalDate plantingDate,
        @NotNull EmbeddedFieldsDto field
) {
}