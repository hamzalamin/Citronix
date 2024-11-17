package com.wora.citronix.services.inter;

import com.wora.citronix.models.DTOs.farmDtos.CreateFarmDto;
import com.wora.citronix.models.DTOs.farmDtos.FarmDto;
import com.wora.citronix.models.DTOs.farmDtos.UpdateFarmDto;
import com.wora.citronix.services.GenericService;

public interface IFarmService extends GenericService<CreateFarmDto, UpdateFarmDto, FarmDto, Long> {
}
