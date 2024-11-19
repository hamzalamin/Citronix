package com.wora.citronix.models.DTOs.treeDtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

public record EmbeddedTreeDto(
        @NotNull @Positive Long id,
        @NotNull LocalDate plantingDate
        ) {
}
