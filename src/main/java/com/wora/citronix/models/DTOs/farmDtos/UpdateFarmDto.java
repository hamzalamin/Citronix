package com.wora.citronix.models.DTOs.farmDtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

public record UpdateFarmDto(
        @NotBlank String name,
        @NotBlank String localization,
        @NotNull @Positive Double surface,
        @NotNull LocalDate creationDate
) {
}
