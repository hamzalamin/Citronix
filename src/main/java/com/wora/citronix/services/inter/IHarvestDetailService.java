package com.wora.citronix.services.inter;

import com.wora.citronix.models.DTOs.harvestDetailDtos.CreateHarvestDetailsDto;
import com.wora.citronix.models.DTOs.harvestDetailDtos.HarvestDetailsDto;
import com.wora.citronix.models.DTOs.harvestDetailDtos.UpdateHarvestDetailsDto;
import com.wora.citronix.models.DTOs.harvestDtos.EmbeddedHarvestDto;
import com.wora.citronix.models.entities.embeddables.HarvestDetailsId;

import java.util.List;

public interface IHarvestDetailService {
    HarvestDetailsDto save(CreateHarvestDetailsDto createHarvestDetailsDto);
    HarvestDetailsDto findById(HarvestDetailsId id);
    HarvestDetailsDto update(UpdateHarvestDetailsDto updateHarvestDetailsDto, HarvestDetailsId id);
    List<HarvestDetailsDto> findAll(Integer pageNumber, Integer size);
    void delete(EmbeddedHarvestDto id);
}
