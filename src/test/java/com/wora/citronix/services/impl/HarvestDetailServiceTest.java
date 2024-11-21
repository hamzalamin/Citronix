package com.wora.citronix.services.impl;

import com.wora.citronix.exceptions.EntityNotFoundException;
import com.wora.citronix.exceptions.PlantingDateException;
import com.wora.citronix.mappers.HarvestDetailsMapper;
import com.wora.citronix.models.DTOs.harvestDetailDtos.CreateHarvestDetailsDto;
import com.wora.citronix.models.DTOs.harvestDetailDtos.HarvestDetailsDto;
import com.wora.citronix.models.entities.Field;
import com.wora.citronix.models.entities.Harvest;
import com.wora.citronix.models.entities.HarvestDetail;
import com.wora.citronix.models.entities.Tree;
import com.wora.citronix.models.enumes.Season;
import com.wora.citronix.repositories.HarvestDetailsRepository;
import com.wora.citronix.services.inter.IHarvestService;
import com.wora.citronix.services.inter.ITreeService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;


@ExtendWith(MockitoExtension.class)
class HarvestDetailServiceTest {
    @Mock
    private HarvestDetailsRepository harvestDetailsRepository;
    @Mock
    private IHarvestService harvestService;
    @Mock
    private HarvestDetailsMapper harvestDetailsMapper;
    @Mock
    private ITreeService treeService;
    @InjectMocks
    private HarvestDetailService sut;

    @Test
    @DisplayName("Should Not Save Harvest Details If Harvest Id Not Found")
    void save_givenNotExistentHarvestId_whenSave_thenThrowEntityNotFoundException() {
        Long harvestId = 1L;
        Long treeId = 1L;

        CreateHarvestDetailsDto createHarvestDetailsDto = new CreateHarvestDetailsDto(treeId, harvestId, 22);
        given(harvestService.findEntityById(harvestId)).willThrow(new EntityNotFoundException("Harvest", harvestId));

        assertThatExceptionOfType(EntityNotFoundException.class)
                .isThrownBy(() -> sut.save(createHarvestDetailsDto))
                .withMessage("Harvest with the id " + harvestId + " not found");
    }

    @Test
    @DisplayName("Should Not Save Harvest Details If tree Id Not Found")
    void save_givenNotExistentTreeId_whenSave_thenThrowEntityNotFoundException() {
        Long harvestId = 1L;
        Long treeId = 1L;
        LocalDate creationDate = LocalDate.parse("2020-07-07");

        CreateHarvestDetailsDto createHarvestDetailsDto = new CreateHarvestDetailsDto(treeId, harvestId, 22);
        Harvest harvest = new Harvest(harvestId, creationDate, Season.SUMMER, List.of());

        given(harvestService.findEntityById(harvestId)).willReturn(harvest);
        given(treeService.findTreeById(treeId)).willThrow(new EntityNotFoundException("Tree", treeId));


        assertThatExceptionOfType(EntityNotFoundException.class)
                .isThrownBy(() -> sut.save(createHarvestDetailsDto))
                .withMessage("Tree with the id " + treeId + " not found");
    }


    @Test
    @DisplayName("Should Not Save Harvest Details If Tree already harvested")
    void save_givenAlreadyHarvestedTree_whenSave_thenThrowPlantingDateException() {
        Long harvestId = 1L;
        Long treeId = 1L;
        LocalDate creationDate = LocalDate.parse("2020-07-07");
        Field field = new Field();

        CreateHarvestDetailsDto createHarvestDetailsDto = new CreateHarvestDetailsDto(treeId, harvestId, 22);
        Harvest harvest = new Harvest(harvestId, creationDate, Season.SUMMER, List.of());
        Tree tree = new Tree(treeId, creationDate, field, List.of());

        given(harvestService.findEntityById(harvestId)).willReturn(harvest);
        given(treeService.findTreeById(treeId)).willReturn(tree);
        given(harvestDetailsRepository.existsByTreeIdAndHarvestSeason(treeId, harvest.getSeason())).willReturn(true);

        assertThatExceptionOfType(PlantingDateException.class)
                .isThrownBy(() -> sut.save(createHarvestDetailsDto))
                .withMessage("This tree has already been harvested in the given season.");
    }


    @Test
    @DisplayName("Should Save Harvest Details When given data is valid")
    void save_givenValidRequest_whenSave_thenThrowSuccess() {
        Long harvestId = 1L;
        Long treeId = 1L;
        LocalDate creationDate = LocalDate.parse("2020-07-07");
        Field field = new Field();

        CreateHarvestDetailsDto createHarvestDetailsDto = new CreateHarvestDetailsDto(treeId, harvestId, 22);
        Harvest harvest = new Harvest(harvestId, creationDate, Season.SUMMER, List.of());
        Tree tree = new Tree(treeId, creationDate, field, List.of());
        HarvestDetail harvestDetail = new HarvestDetail(harvest, tree, 22);
        HarvestDetailsDto harvestDetailsDto = new HarvestDetailsDto(harvestDetail.getId(), 22, null, null);

        given(harvestService.findEntityById(harvestId)).willReturn(harvest);
        given(treeService.findTreeById(treeId)).willReturn(tree);
        given(harvestDetailsRepository.existsByTreeIdAndHarvestSeason(treeId, harvest.getSeason())).willReturn(false);
        given(harvestDetailsRepository.save(any(HarvestDetail.class))).willReturn(harvestDetail);
        given(harvestDetailsMapper.toDto(harvestDetail)).willReturn(harvestDetailsDto);

        HarvestDetailsDto actual = sut.save(createHarvestDetailsDto);
        assertThat(actual).isNotNull();
        assertThat(actual.id()).isEqualTo(harvestDetail.getId());

    }




}

















