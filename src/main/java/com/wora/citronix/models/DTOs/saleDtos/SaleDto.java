package com.wora.citronix.models.DTOs.saleDtos;

import com.wora.citronix.models.DTOs.harvestDtos.EmbeddedHarvestDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

public record SaleDto(
        @NotNull @Positive Long id,
        @NotBlank String clientName,
        @NotNull Double unitPrice,
        @NotNull LocalDate saleDate,
        @NotNull @Positive Double saleQuantity,
        EmbeddedHarvestDto harvest
) {
    public Double getRevenue(){
        return unitPrice * saleQuantity;
    }
}
