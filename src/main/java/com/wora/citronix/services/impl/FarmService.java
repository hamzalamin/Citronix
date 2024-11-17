package com.wora.citronix.services.impl;

import com.wora.citronix.mappers.FarmMapper;
import com.wora.citronix.models.DTOs.farmDtos.CreateFarmDto;
import com.wora.citronix.models.DTOs.farmDtos.FarmDto;
import com.wora.citronix.models.DTOs.farmDtos.UpdateFarmDto;
import com.wora.citronix.models.entities.Farm;
import com.wora.citronix.repositories.FarmRepository;
import com.wora.citronix.services.inter.IFarmService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FarmService implements IFarmService {

    private final FarmRepository farmRepository;
    private final FarmMapper farmMapper;

    @Override
    public FarmDto save(CreateFarmDto createFarmDto) {
        Farm farm = farmMapper.toEntity(createFarmDto);
        Farm savedFarm = farmRepository.save(farm);
        return farmMapper.toDto(savedFarm);
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
