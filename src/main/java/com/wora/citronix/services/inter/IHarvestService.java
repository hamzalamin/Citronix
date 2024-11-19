package com.wora.citronix.services.inter;

import com.wora.citronix.models.DTOs.harvestDtos.CreateHarvestDto;
import com.wora.citronix.models.DTOs.harvestDtos.HarvestDto;
import com.wora.citronix.models.DTOs.harvestDtos.UpdateHarvestDto;
import com.wora.citronix.services.GenericService;

public interface IHarvestService extends GenericService<CreateHarvestDto, UpdateHarvestDto, HarvestDto, Long> {
}
