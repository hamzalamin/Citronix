package com.wora.citronix.services.impl;

import com.wora.citronix.exceptions.EntityNotFoundException;
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
import org.springframework.data.domain.PageRequest;
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
        Farm farm = getFarmById(createFieldDto.farmId());

        Double totalFieldsSurface = calculateFieldsSurface(farm);
        Double halfFarmSurface = farm.getSurface() / 2;
        Double farmSurface = farm.getSurface();
        Double fieldSurface = field.getSurface();

        if (calculateFieldsNumber(farm) >= 10) {
            throw new InsufficientFarmSurfaceException("The maximum number of fields allowed on a farm is 10");
        }
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
        Field field = fieldRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Field", id));
        return fieldMapper.toDto(field);
    }

    @Override
    public FieldDto update(UpdateFieldDto updateFieldDto, Long id) {
        Field field = fieldRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Field", id));

        Farm farm = getFarmById(updateFieldDto.farmId());

        Double totalFieldsSurface = calculateFieldsSurface(farm);
        Double halfFarmSurface = farm.getSurface() / 2;
        Double farmSurface = farm.getSurface();
        Double fieldSurface = field.getSurface();

        if (calculateFieldsNumber(farm) >= 10) {
            throw new InsufficientFarmSurfaceException("The maximum number of fields allowed on a farm is 10");
        }
        if (fieldSurface > halfFarmSurface) {
            throw new InsufficientFarmSurfaceException("Field must be under 50% of the farm surface");
        }
        if (farmSurface <= totalFieldsSurface + fieldSurface) {
            throw new InsufficientFarmSurfaceException("Farm surface area is insufficient for the new field");
        }

        field.setName(updateFieldDto.name());
        field.setSurface(updateFieldDto.surface());
        Field savedField = fieldRepository.save(field);
        return fieldMapper.toDto(savedField);
    }

    @Override
    public List<FieldDto> findAll(Integer pageNumber, Integer size) {
        PageRequest pageable = PageRequest.of(pageNumber, size);
        return fieldRepository.findAll(pageable).stream()
                .map(fieldMapper::toDto)
                .toList();
    }

    @Override
    public void delete(Long id) {
        Field field = fieldRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Field", id));
        fieldRepository.delete(field);
    }

    @Override
    public Double calculateFieldsSurface(Farm farm) {
        return fieldRepository.findByFarm(farm).stream()
                .mapToDouble(Field::getSurface)
                .sum();
    }

    private Integer calculateFieldsNumber(Farm farm) {
        return fieldRepository.findByFarm(farm).size();
    }

    private Farm getFarmById(Long farmId) {
        return farmService.getFarmEntityById(farmId);
    }
}
