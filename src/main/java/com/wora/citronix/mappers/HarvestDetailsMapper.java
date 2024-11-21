package com.wora.citronix.mappers;

import com.wora.citronix.mappers.api.GenericMapper;
import com.wora.citronix.models.DTOs.harvestDetailDtos.CreateHarvestDetailsDto;
import com.wora.citronix.models.DTOs.harvestDetailDtos.HarvestDetailsDto;
import com.wora.citronix.models.DTOs.harvestDetailDtos.UpdateHarvestDetailsDto;
import com.wora.citronix.models.entities.HarvestDetail;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface HarvestDetailsMapper extends GenericMapper<HarvestDetail, HarvestDetailsDto> {
    HarvestDetail toEntity(HarvestDetailsDto harvestDetailsDto);
    HarvestDetail toEntity(CreateHarvestDetailsDto createHarvestDetailsDto);
    HarvestDetail toEntity(UpdateHarvestDetailsDto updateHarvestDetailsDto);
    HarvestDetailsDto toDto(HarvestDetail harvestDetail);
}
