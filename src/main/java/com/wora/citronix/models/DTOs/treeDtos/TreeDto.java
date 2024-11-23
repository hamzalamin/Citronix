package com.wora.citronix.models.DTOs.treeDtos;

import com.wora.citronix.models.DTOs.fieldDtos.EmbeddedFieldsDto;
import com.wora.citronix.models.DTOs.harvestDetailDtos.EmbeddedHarvestDetailDto;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

public record TreeDto(
        @NotNull @Positive Long id,
        @NotNull LocalDate plantingDate,
        @NotNull EmbeddedFieldsDto field,
        @NotNull List<EmbeddedHarvestDetailDto> harvestDetails
) {

    public int getAge() {
        return Period.between(plantingDate, LocalDate.now()).getYears();
    }

    public double getProductivityByKg() {
        int age = getAge();
        if (age <= 3) {
            return 2.5 * 4;
        }
        if (age > 3 && age <= 10) {
            return 12 * 4;
        }
        if (age > 10 && age <= 20) {
            return 20 * 4;
        }
        if (age > 20) {
            return 0;
        }
        return 0;
    }

}
