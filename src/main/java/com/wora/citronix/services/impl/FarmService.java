package com.wora.citronix.services.impl;

import com.wora.citronix.models.DTOs.farmDtos.CreateFarmDto;
import com.wora.citronix.models.DTOs.farmDtos.FarmDto;
import com.wora.citronix.models.DTOs.farmDtos.UpdateFarmDto;
import com.wora.citronix.services.inter.IFarmService;

import java.util.List;

public class FarmService implements IFarmService {
    @Override
    public FarmDto save(CreateFarmDto createFarmDto) {
        return null;
    }

    @Override
    public FarmDto findById(Long id) {
        return null;
    }

    @Override
    public FarmDto update(UpdateFarmDto updateFarmDto, Long id) {
        return null;
    }

    @Override
    public List<FarmDto> findAll(Integer pageNumber, Integer size) {
        return List.of();
    }

    @Override
    public void delete(Long id) {

    }
}
