package com.wora.citronix.models.DTOs.treeDtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

public record UpdateTreeDto(
        @NotNull LocalDate plantingDate,
        @NotNull @Positive Long fieldId
) {
}
