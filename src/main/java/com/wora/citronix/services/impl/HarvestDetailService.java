package com.wora.citronix.services.impl;

import com.wora.citronix.models.DTOs.harvestDetailDtos.CreateHarvestDetailsDto;
import com.wora.citronix.models.DTOs.harvestDetailDtos.HarvestDetailsDto;
import com.wora.citronix.models.DTOs.harvestDetailDtos.UpdateHarvestDetailsDto;
import com.wora.citronix.services.inter.IHarvestDetailService;

import java.util.List;

public class HarvestDetailService implements IHarvestDetailService {
    @Override
    public HarvestDetailsDto save(CreateHarvestDetailsDto createHarvestDetailsDto) {
        return null;
    }

    @Override
    public HarvestDetailsDto findById(Long aLong) {
        return null;
    }

    @Override
    public HarvestDetailsDto update(UpdateHarvestDetailsDto updateHarvestDetailsDto, Long aLong) {
        return null;
    }

    @Override
    public List<HarvestDetailsDto> findAll(Integer pageNumber, Integer size) {
        return List.of();
    }

    @Override
    public void delete(Long aLong) {

    }
}
