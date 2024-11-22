package com.wora.citronix.services.impl;

import com.wora.citronix.exceptions.EntityNotFoundException;
import com.wora.citronix.exceptions.PlantingDateException;
import com.wora.citronix.mappers.SaleMapper;
import com.wora.citronix.models.DTOs.saleDtos.CreateSaleDto;
import com.wora.citronix.models.entities.Harvest;
import com.wora.citronix.models.enumes.Season;
import com.wora.citronix.repositories.SaleRepository;
import com.wora.citronix.services.inter.IHarvestService;
import com.wora.citronix.services.inter.ISaleQuantityValidatorService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class SaleServiceTest {
    @Mock
    private SaleRepository saleRepository;
    @Mock
    private SaleMapper saleMapper;
    @Mock
    private ISaleQuantityValidatorService iSaleQuantityValidatorService;
    @Mock
    private IHarvestService harvestService;
    @InjectMocks
    private SaleService sut;


    @Test
    @DisplayName("save() Should Thrown Entity not found Exception When Harvest Id Not Found")
    void save_ShouldThrownEntityNotFoundExceptionWhenHarvestIdNotFound() {
        LocalDate saleDate = LocalDate.parse("2024-07-07");
        LocalDate creationDate = LocalDate.parse("2024-01-01");

        Harvest harvest = new Harvest(1L ,creationDate, Season.WINTER, List.of(), List.of());
        CreateSaleDto createSaleDto = new  CreateSaleDto("HAMZA", 12.2, saleDate, 12.2, harvest.getId());

        given(harvestService.findEntityById(harvest.getId())).willThrow(new EntityNotFoundException("Harvest", harvest.getId()));

        assertThatExceptionOfType(EntityNotFoundException.class)
                .isThrownBy(() -> sut.save(createSaleDto))
                .withMessage("Harvest with the id " + harvest.getId() + " not found");
    }

    @Test
    @DisplayName("save Should Throw Planting Date Exception When the Quantity Wanted more then Quantity Harvested")
    void save_ShouldThrowPlantingDateExceptionWhenTheQuantityWantedMoreThenQuantityHarvested(){
        LocalDate saleDate = LocalDate.parse("2024-07-07");
        LocalDate creationDate = LocalDate.parse("2024-01-01");

        Harvest harvest = new Harvest(1L ,creationDate, Season.WINTER, List.of(), List.of());
        CreateSaleDto createSaleDto = new  CreateSaleDto("HAMZA", 12.2, saleDate, 12.2, harvest.getId());

        given(harvestService.findEntityById(harvest.getId())).willReturn(harvest);
        doThrow(new PlantingDateException("the wanted quantity does not exist"))
                .when(iSaleQuantityValidatorService)
                .ensureWantedQuantityExist(12.2, harvest.getId());

        assertThatExceptionOfType(PlantingDateException.class)
                .isThrownBy(() -> sut.save(createSaleDto))
                .withMessage("the wanted quantity does not exist");

    }

}