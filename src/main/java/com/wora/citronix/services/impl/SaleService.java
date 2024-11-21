package com.wora.citronix.services.impl;

import com.wora.citronix.models.DTOs.saleDtos.CreateSaleDto;
import com.wora.citronix.models.DTOs.saleDtos.SaleDto;
import com.wora.citronix.models.DTOs.saleDtos.UpdateSaleDto;
import com.wora.citronix.services.inter.ISaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SaleService implements ISaleService {
    @Override
    public SaleDto save(CreateSaleDto createSaleDto) {
        return null;
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
