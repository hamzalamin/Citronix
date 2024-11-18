package com.wora.citronix.services.impl;

import com.wora.citronix.exceptions.InsufficientFarmSurfaceException;
import com.wora.citronix.mappers.FieldMapper;
import com.wora.citronix.models.DTOs.fieldDtos.CreateFieldDto;
import com.wora.citronix.models.DTOs.fieldDtos.FieldDto;
import com.wora.citronix.models.DTOs.fieldDtos.UpdateFieldDto;
import com.wora.citronix.models.entities.Farm;
import com.wora.citronix.models.entities.Field;
import com.wora.citronix.repositories.FieldRepository;
import com.wora.citronix.services.inter.IFarmService;
import com.wora.citronix.services.inter.IFieldService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FieldService implements IFieldService {

    private final FieldRepository fieldRepository;
    private final FieldMapper fieldMapper;
    private final IFarmService farmService;

    @Override
    public FieldDto save(CreateFieldDto createFieldDto) {
        Field field = fieldMapper.toEntity(createFieldDto);
        Farm farm = farmService.getFarmEntityById(createFieldDto.farmId());
        Double totalFieldsSurface = calculateFieldsSurface(farm);
        Double halfFarmSurface = farm.getSurface() / 2;
        Double farmSurface = farm.getSurface();
        Double fieldSurface = field.getSurface();

        if (fieldSurface > halfFarmSurface) {
            throw new InsufficientFarmSurfaceException("Field must be under 50% of the farm surface");
        }
        if (farmSurface <= totalFieldsSurface + fieldSurface) {
            throw new InsufficientFarmSurfaceException("Farm surface area is insufficient for the new field");
        }
        Field savedField = fieldRepository.save(field);
        return fieldMapper.toDto(savedField);
    }

    @Override
    public FieldDto findById(Long id) {
        return null;
    }

    @Override
    public FieldDto update(UpdateFieldDto updateFieldDto, Long id) {
        return null;
    }

    @Override
    public List<FieldDto> findAll(Integer pageNumber, Integer size) {
        return List.of();
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Double calculateFieldsSurface(Farm farm) {
        return fieldRepository.findByFarm(farm).stream()
                .mapToDouble(Field::getSurface)
                .sum();
    }
}
