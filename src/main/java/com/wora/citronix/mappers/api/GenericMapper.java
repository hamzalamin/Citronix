package com.wora.citronix.mappers.api;

public interface GenericMapper<ENTITY, DTO> {
    ENTITY toEntity(ENTITY entity);
    DTO toDto(DTO dto);
}
