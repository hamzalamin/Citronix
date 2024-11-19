package com.wora.citronix.services.impl;

import com.wora.citronix.models.DTOs.harvestDtos.CreateHarvestDto;
import com.wora.citronix.models.DTOs.harvestDtos.HarvestDto;
import com.wora.citronix.models.DTOs.harvestDtos.UpdateHarvestDto;
import com.wora.citronix.services.inter.IHarvestService;

import java.util.List;

public class HarvestService implements IHarvestService {
    @Override
    public HarvestDto save(CreateHarvestDto createHarvestDto) {
        return null;
    }

    @Override
    public HarvestDto findById(Long id) {
        return null;
    }

    @Override
    public HarvestDto update(UpdateHarvestDto updateHarvestDto, Long id) {
        return null;
    }

    @Override
    public List<HarvestDto> findAll(Integer pageNumber, Integer size) {
        return List.of();
    }

    @Override
    public void delete(Long id) {

    }
}
