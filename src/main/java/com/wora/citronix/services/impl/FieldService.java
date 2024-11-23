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
import java.util.Optional;

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

        System.out.println("id dyal farm : " + farm.getId());
        System.out.println("field number dyal farm : " + calculateFieldsNumber(farm));
        System.out.println("surface total fields : " + totalFieldsSurface);

        if (calculateFieldsNumber(farm) >= 10) {
            throw new InsufficientFarmSurfaceException("The maximum number of fields allowed on a farm is 10");
        }
        if (fieldSurface > halfFarmSurface) {
            throw new InsufficientFarmSurfaceException("Field must be under 50% of the farm surface");
        }
        if (farmSurface <= totalFieldsSurface + fieldSurface) {
            throw new InsufficientFarmSurfaceException("Farm surface area is insufficient for the new field");
        }
        if (fieldSurface < 0.1){
            throw new InsufficientFarmSurfaceException("Surface of Field must be more then 0.1 Hectar");
        }

        field.setFarm(farm);
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

        Farm farm = Optional.ofNullable(updateFieldDto.farmId())
                .map(farmId -> getFarmById(farmId))
                .orElse(field.getFarm());

        Double newFieldSurface = Optional.ofNullable(updateFieldDto.surface())
                .orElse(field.getSurface());

        Double halfFarmSurface = farm.getSurface() / 2;

        Double totalOtherFieldsSurface = calculateFieldsSurface(farm) -
                (field.getFarm().getId().equals(farm.getId()) ? field.getSurface() : 0);

        if (!field.getFarm().getId().equals(farm.getId()) && calculateFieldsNumber(farm) >= 10) {
            throw new InsufficientFarmSurfaceException("The maximum number of fields allowed on a farm is 10");
        }
        if (newFieldSurface < 0.1) {
            throw new InsufficientFarmSurfaceException("Surface of Field must be more than 0.1 Hectar");
        }
        if (newFieldSurface > halfFarmSurface) {
            throw new InsufficientFarmSurfaceException("Field must be under 50% of the farm surface");
        }
        if (farm.getSurface() < totalOtherFieldsSurface + newFieldSurface) {
            throw new InsufficientFarmSurfaceException("Farm surface area is insufficient for the new field");
        }

        field.setFarm(farm);
        Optional.ofNullable(updateFieldDto.name())
                .ifPresent(field::setName);
        Optional.ofNullable(updateFieldDto.surface())
                .ifPresent(field::setSurface);

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

    @Override
    public Field getFieldEntityById(Long id) {
        return fieldRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("field", id));
    }

    public Integer calculateFieldsNumber(Farm farm) {
        return fieldRepository.findByFarm(farm).size();
    }

    private Farm getFarmById(Long farmId) {
        return farmService.getFarmEntityById(farmId);
    }
}
