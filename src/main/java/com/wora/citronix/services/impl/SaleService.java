package com.wora.citronix.services.impl;

import com.wora.citronix.exceptions.DuplicateHarvestException;
import com.wora.citronix.exceptions.EntityNotFoundException;
import com.wora.citronix.mappers.SaleMapper;
import com.wora.citronix.models.DTOs.saleDtos.CreateSaleDto;
import com.wora.citronix.models.DTOs.saleDtos.SaleDto;
import com.wora.citronix.models.DTOs.saleDtos.UpdateSaleDto;
import com.wora.citronix.models.entities.Harvest;
import com.wora.citronix.models.entities.Sale;
import com.wora.citronix.repositories.SaleRepository;
import com.wora.citronix.services.inter.IHarvestService;
import com.wora.citronix.services.inter.ISaleQuantityValidatorService;
import com.wora.citronix.services.inter.ISaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SaleService implements ISaleService {
    private final SaleRepository saleRepository;
    private final SaleMapper saleMapper;
    private final IHarvestService harvestService;
    private final ISaleQuantityValidatorService saleQuantityValidatorService;

    @Override
    public SaleDto save(CreateSaleDto createSaleDto) {
        Harvest harvest = harvestService.findEntityById(createSaleDto.harvestId());
        if (harvest.getHarvestDetails() == null || harvest.getHarvestDetails().isEmpty()) {
            throw new DuplicateHarvestException("Cannot create sale for harvest without harvest details");
        }
        saleQuantityValidatorService.ensureWantedQuantityExist(createSaleDto.saleQuantity(), createSaleDto.harvestId());
        Sale sale = Sale.builder()
                .harvest(harvest)
                .clientName(createSaleDto.clientName())
                .saleQuantity(createSaleDto.saleQuantity())
                .saleDate(createSaleDto.saleDate())
                .unitPrice(createSaleDto.unitPrice())
                .build();
        Sale savedSale = saleRepository.save(sale);
        return saleMapper.toDto(savedSale);
    }

    @Override
    public SaleDto findById(Long id) {
        Sale sale = saleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Sale", id));
        return saleMapper.toDto(sale);
    }

    @Override
    public SaleDto update(UpdateSaleDto updateSaleDto, Long id) {
        Sale sale = saleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Sale", id));
        Harvest harvest = harvestService.findEntityById(updateSaleDto.harvestId());
        if (harvest.getHarvestDetails() == null || harvest.getHarvestDetails().isEmpty()) {
            throw new DuplicateHarvestException("Cannot create sale for harvest without harvest details");
        }
        saleQuantityValidatorService.ensureWantedQuantityExistForUpdate(updateSaleDto.saleQuantity(), updateSaleDto.harvestId(), sale.getId());
        sale.setHarvest(harvest)
                .setSaleDate(updateSaleDto.saleDate())
                .setClientName(updateSaleDto.clientName())
                .setSaleQuantity(updateSaleDto.saleQuantity())
                .setUnitPrice(updateSaleDto.unitPrice());
        Sale savedSale = saleRepository.save(sale);
        return saleMapper.toDto(savedSale);
    }

    @Override
    public List<SaleDto> findAll(Integer pageNumber, Integer size) {
        PageRequest pageable = PageRequest.of(pageNumber, size);
        return saleRepository.findAll(pageable).stream()
                .map(saleMapper::toDto)
                .toList();
    }

    @Override
    public void delete(Long id) {
        Sale sale = saleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Sale", id));
        saleRepository.delete(sale);
    }
}
