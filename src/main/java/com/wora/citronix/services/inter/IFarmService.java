package com.wora.citronix.services.inter;

import com.wora.citronix.models.DTOs.farmDtos.CreateFarmDto;
import com.wora.citronix.models.DTOs.farmDtos.FarmDto;
import com.wora.citronix.models.DTOs.farmDtos.UpdateFarmDto;
import com.wora.citronix.services.GenericService;

import java.time.LocalDate;
import java.util.List;

public interface IFarmService extends GenericService<CreateFarmDto, UpdateFarmDto, FarmDto, Long> {
    List<FarmDto> search(String name, String localization, Double surface, LocalDate creationDate);
}
