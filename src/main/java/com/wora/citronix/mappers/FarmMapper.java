package com.wora.citronix.mappers;

import com.wora.citronix.mappers.api.GenericMapper;
import com.wora.citronix.models.DTOs.farmDtos.CreateFarmDto;
import com.wora.citronix.models.DTOs.farmDtos.FarmDto;
import com.wora.citronix.models.DTOs.farmDtos.UpdateFarmDto;
import com.wora.citronix.models.entities.Farm;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FarmMapper extends GenericMapper<Farm, FarmDto> {
    Farm toEntity(FarmDto dto);
    Farm toEntity(CreateFarmDto dto);
    Farm toEntity(UpdateFarmDto dto);
    FarmDto toDto(Farm farm);
}
