package com.wora.citronix.services.inter;

import com.wora.citronix.models.DTOs.harvestDetailDtos.CreateHarvestDetailsDto;
import com.wora.citronix.models.DTOs.harvestDetailDtos.HarvestDetailsDto;
import com.wora.citronix.models.DTOs.harvestDetailDtos.UpdateHarvestDetailsDto;
import com.wora.citronix.services.GenericService;

public interface IHarvestDetailService extends GenericService<CreateHarvestDetailsDto, UpdateHarvestDetailsDto, HarvestDetailsDto, Long> {
}
