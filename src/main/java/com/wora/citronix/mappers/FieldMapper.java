package com.wora.citronix.mappers;

import com.wora.citronix.mappers.api.GenericMapper;
import com.wora.citronix.models.DTOs.fieldsDtos.FieldDto;
import com.wora.citronix.models.entities.Field;

public interface FieldMapper extends GenericMapper<Field, FieldDto> {
    Field toEntity(FieldDto fieldDto);
    FieldDto toDto(Field field);
}
