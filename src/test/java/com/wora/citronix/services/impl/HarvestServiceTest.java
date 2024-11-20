package com.wora.citronix.services.impl;

import com.wora.citronix.mappers.HarvestMapper;
import com.wora.citronix.models.DTOs.harvestDtos.CreateHarvestDto;
import com.wora.citronix.models.DTOs.harvestDtos.HarvestDto;
import com.wora.citronix.models.entities.Farm;
import com.wora.citronix.models.entities.Harvest;
import com.wora.citronix.models.enumes.Season;
import com.wora.citronix.repositories.HarvestRepository;
import com.wora.citronix.services.inter.IFarmService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class HarvestServiceTest {

    @Mock
    private HarvestRepository harvestRepository;
    @Mock
    private HarvestMapper harvestMapper;
    @Mock
    private IFarmService farmService;

    @InjectMocks
    private HarvestService sut;

//        @Test
//        @DisplayName("save() harvest successfully when all conditions are met")
//        void save_Saveharvestsuccessfullywhenallconditionsaremet() {
//            LocalDate creationDate = LocalDate.parse("2020-05-05");
//            LocalDate creationDate2 = LocalDate.parse("2020-01-01");
//            Season season = Season.SPRING;
//            Farm farm = new Farm(1L, "NAME X", "LOCALIZATION Y", 1.2, creationDate2, List.of(), List.of());
//            CreateHarvestDto createHarvestDto = new CreateHarvestDto(creationDate, season, farm.getId());
//            Harvest harvest = new Harvest(1L, creationDate, season, farm, List.of());
//            HarvestDto expectedHarvestDto = new HarvestDto(1L, creationDate, season, );
//
//            when(harvestMapper.toEntity(createHarvestDto)).thenReturn(harvest);
//            when(farmService.getFarmEntityById(farm.getId())).thenReturn(farm);
//            when(harvestRepository.save(harvest)).thenReturn(harvest);
//            when(harvestMapper.toDto(harvest)).thenReturn(expectedHarvestDto);
//
//
//
//        }
}