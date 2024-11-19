package com.wora.citronix.services.impl;

import com.wora.citronix.exceptions.NotSameSeasonException;
import com.wora.citronix.mappers.HarvestMapper;
import com.wora.citronix.models.DTOs.harvestDtos.CreateHarvestDto;
import com.wora.citronix.models.DTOs.harvestDtos.HarvestDto;
import com.wora.citronix.models.DTOs.harvestDtos.UpdateHarvestDto;
import com.wora.citronix.models.entities.Harvest;
import com.wora.citronix.models.enumes.Season;
import com.wora.citronix.repositories.HarvestRepository;
import com.wora.citronix.services.inter.IHarvestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HarvestService implements IHarvestService {
    private final HarvestRepository harvestRepository;
    private final HarvestMapper harvestMapper;

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
