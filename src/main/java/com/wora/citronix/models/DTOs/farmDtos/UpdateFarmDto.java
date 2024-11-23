package com.wora.citronix.models.DTOs.farmDtos;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

public record UpdateFarmDto(
        String name,
        String localization,
        @Positive @DecimalMin(value = "0.2", message = "The min surface value of farm must be 0.2 Hectar")
        Double surface,
        @NotNull LocalDate creationDate
) {
}
