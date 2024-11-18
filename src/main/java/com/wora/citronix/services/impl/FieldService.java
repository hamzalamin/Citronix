package com.wora.citronix.services.impl;

import com.wora.citronix.models.DTOs.fieldDtos.CreateFieldDto;
import com.wora.citronix.models.DTOs.fieldDtos.FieldDto;
import com.wora.citronix.models.DTOs.fieldDtos.UpdateFieldDto;
import com.wora.citronix.services.inter.IFieldService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FieldService implements IFieldService {
    @Override
    public FieldDto save(CreateFieldDto createFieldDto) {
        return null;
    }

    @Override
    public FieldDto findById(Long aLong) {
        return null;
    }

    @Override
    public FieldDto update(UpdateFieldDto updateFieldDto, Long aLong) {
        return null;
    }

    @Override
    public List<FieldDto> findAll(Integer pageNumber, Integer size) {
        return List.of();
    }

    @Override
    public void delete(Long aLong) {

    }
}
