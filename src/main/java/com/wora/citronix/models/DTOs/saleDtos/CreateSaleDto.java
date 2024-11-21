package com.wora.citronix.models.DTOs.saleDtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

public record CreateSaleDto(
        @NotBlank String clientName,
        @NotNull Double unitPrice,
        @NotNull LocalDate saleDate,
        @NotNull @Positive Double saleQuantity,
        @NotNull @Positive Long harvestId
) {
}
