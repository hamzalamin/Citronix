package com.wora.citronix.services.impl;

import com.wora.citronix.mappers.SaleMapper;
import com.wora.citronix.models.DTOs.saleDtos.CreateSaleDto;
import com.wora.citronix.models.DTOs.saleDtos.SaleDto;
import com.wora.citronix.models.DTOs.saleDtos.UpdateSaleDto;
import com.wora.citronix.models.entities.Harvest;
import com.wora.citronix.models.entities.Sale;
import com.wora.citronix.repositories.SaleRepository;
import com.wora.citronix.services.inter.IHarvestService;
import com.wora.citronix.services.inter.ISaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SaleService implements ISaleService{
    private final SaleRepository saleRepository;
    private final SaleMapper saleMapper;
    private final IHarvestService harvestService;
    private final SaleQuantityValidatorService saleQuantityValidatorService;

    @Override
    public SaleDto save(CreateSaleDto createSaleDto) {
        Sale sale = saleMapper.toEntity(createSaleDto);
        Harvest harvest = harvestService.findEntityById(createSaleDto.harvestId());
        saleQuantityValidatorService.ensureWantedQuantityExist(createSaleDto.saleQuantity(), createSaleDto.harvestId());
        sale.setHarvest(harvest);
        Sale savedSale = saleRepository.save(sale);
        return saleMapper.toDto(savedSale);
    }

    @Override
    public SaleDto findById(Long aLong) {
        return null;
    }

    @Override
    public SaleDto update(UpdateSaleDto updateSaleDto, Long aLong) {
        return null;
    }

    @Override
    public List<SaleDto> findAll(Integer pageNumber, Integer size) {
        return List.of();
    }

    @Override
    public void delete(Long aLong) {

    }
}
