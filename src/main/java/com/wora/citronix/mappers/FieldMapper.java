package com.wora.citronix.mappers;

import com.wora.citronix.mappers.api.GenericMapper;
import com.wora.citronix.models.DTOs.fieldDtos.CreateFieldDto;
import com.wora.citronix.models.DTOs.fieldDtos.FieldDto;
import com.wora.citronix.models.DTOs.fieldDtos.UpdateFieldDto;
import com.wora.citronix.models.entities.Field;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FieldMapper extends GenericMapper<Field, FieldDto> {
    Field toEntity(FieldDto fieldDto);
    Field toEntity(CreateFieldDto createFieldDto);
    Field toEntity(UpdateFieldDto updateFieldDto);
    FieldDto toDto(Field field);
}
