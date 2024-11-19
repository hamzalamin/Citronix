package com.wora.citronix.mappers;

import com.wora.citronix.mappers.api.GenericMapper;
import com.wora.citronix.models.DTOs.harvestDtos.CreateHarvestDto;
import com.wora.citronix.models.DTOs.harvestDtos.HarvestDto;
import com.wora.citronix.models.DTOs.harvestDtos.UpdateHarvestDto;
import com.wora.citronix.models.entities.Harvest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface HarvestMapper extends GenericMapper<Harvest, HarvestDto> {
    Harvest toEntity(HarvestDto harvestDto);
    Harvest toEntity(CreateHarvestDto createHarvestDto);
    Harvest toEntity(UpdateHarvestDto updateHarvestDto);
    HarvestDto toDto(Harvest harvest);

}
