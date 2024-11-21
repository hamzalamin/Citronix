package com.wora.citronix.mappers;

import com.wora.citronix.mappers.api.GenericMapper;
import com.wora.citronix.models.DTOs.saleDtos.CreateSaleDto;
import com.wora.citronix.models.DTOs.saleDtos.SaleDto;
import com.wora.citronix.models.DTOs.saleDtos.UpdateSaleDto;
import com.wora.citronix.models.entities.Sale;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SaleMapper extends GenericMapper<Sale, SaleDto> {
    Sale toEntity(SaleDto saleDto);
    Sale toEntity(CreateSaleDto createSaleDto);
    Sale toEntity(UpdateSaleDto updateSaleDto);
    SaleDto toDto(Sale sale);
}
