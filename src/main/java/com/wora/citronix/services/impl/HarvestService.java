package com.wora.citronix.services.impl;

import com.wora.citronix.exceptions.EntityNotFoundException;
import com.wora.citronix.exceptions.HarvestAlreadyExistsException;
import com.wora.citronix.exceptions.NotSameSeasonException;
import com.wora.citronix.mappers.HarvestMapper;
import com.wora.citronix.models.DTOs.harvestDtos.CreateHarvestDto;
import com.wora.citronix.models.DTOs.harvestDtos.HarvestDto;
import com.wora.citronix.models.DTOs.harvestDtos.UpdateHarvestDto;
import com.wora.citronix.models.entities.Farm;
import com.wora.citronix.models.entities.Harvest;
import com.wora.citronix.models.enumes.Season;
import com.wora.citronix.repositories.HarvestRepository;
import com.wora.citronix.services.inter.IFarmService;
import com.wora.citronix.services.inter.IHarvestService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HarvestService implements IHarvestService {
    private final HarvestRepository harvestRepository;
    private final HarvestMapper harvestMapper;
    private final IFarmService farmService;

    @Override
    public HarvestDto save(CreateHarvestDto createHarvestDto) {
        Harvest harvest = harvestMapper.toEntity(createHarvestDto);
        if (!isSameSeason(createHarvestDto.creationDate(), createHarvestDto.season())){
            throw new NotSameSeasonException("The creation date is not in the same season.");
        }
        Harvest savedHarvest = harvestRepository.save(harvest);
        return harvestMapper.toDto(savedHarvest);
    }

    @Override
    public HarvestDto findById(Long id) {
        Harvest harvest = harvestRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Harvest", id));
        return harvestMapper.toDto(harvest);
    }

    @Override
    public HarvestDto update(UpdateHarvestDto updateHarvestDto, Long id) {
        Harvest harvest = harvestRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Harvest", id));
        if (!isSameSeason(updateHarvestDto.creationDate(), updateHarvestDto.season())){
            throw new NotSameSeasonException("The creation date is not in the same season.");
        }

        harvest.setCreationDate(updateHarvestDto.creationDate());
        harvest.setSeason(updateHarvestDto.season());
        Harvest savedHarvest = harvestRepository.save(harvest);
        return harvestMapper.toDto(savedHarvest);
    }

    @Override
    public List<HarvestDto> findAll(Integer pageNumber, Integer size) {
        PageRequest pageable = PageRequest.of(pageNumber, size);
        return harvestRepository.findAll(pageable).stream()
                .map(harvestMapper::toDto)
                .toList();
    }

    @Override
    public void delete(Long id) {
        Harvest harvest = harvestRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Harvest", id));
        harvestRepository.delete(harvest);
    }

    @Override
    public Harvest findEntityById(Long id){
        return harvestRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Harvest", id));
    }

    public boolean isSameSeason(LocalDate creationDate, Season season) {
        int month = creationDate.getMonthValue();

        switch (season) {
            case SPRING:
                return month >= 3 && month <= 5;
            case SUMMER:
                return month >= 6 && month <= 8;
            case AUTUMN:
                return month >= 9 && month <= 11;
            case WINTER:
                return month == 12 || month == 1 || month == 2;
            default:
                throw new IllegalArgumentException("Unknown season: " + season);
        }
    }


}
