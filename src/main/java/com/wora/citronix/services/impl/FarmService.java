package com.wora.citronix.services.impl;

import com.wora.citronix.exceptions.EntityNotFoundException;
import com.wora.citronix.exceptions.InsufficientFarmSurfaceException;
import com.wora.citronix.mappers.FarmMapper;
import com.wora.citronix.models.DTOs.farmDtos.CreateFarmDto;
import com.wora.citronix.models.DTOs.farmDtos.FarmDto;
import com.wora.citronix.models.DTOs.farmDtos.UpdateFarmDto;
import com.wora.citronix.models.entities.Farm;
import com.wora.citronix.repositories.FarmRepository;
import com.wora.citronix.services.inter.IFarmService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FarmService implements IFarmService {

    private final FarmRepository farmRepository;
    private final FarmMapper farmMapper;

    @Override
    public FarmDto save(CreateFarmDto createFarmDto) {
        Farm farm = farmMapper.toEntity(createFarmDto);
        if (farm.getSurface() < 0.2){
            throw new InsufficientFarmSurfaceException("The min surface value of farm must be 0.2 Hectar");
        }
        Farm savedFarm = farmRepository.save(farm);
        return farmMapper.toDto(savedFarm);
    }

    @Override
    public FarmDto findById(Long id) {
        Farm farm = farmRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Farm", id));
        return farmMapper.toDto(farm);
    }

    @Override
    public FarmDto update(UpdateFarmDto updateFarmDto, Long id) {
        Farm farm = farmRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Farm", id));
        if (farm.getSurface() < 0.2){
            throw new InsufficientFarmSurfaceException("The min surface value of farm must be 0.2 Hectar");
        }

        Optional.ofNullable(updateFarmDto.name())
                .ifPresent(farm::setName);
        Optional.ofNullable(updateFarmDto.localization())
                        .ifPresent(farm::setLocalization);
        Optional.ofNullable(updateFarmDto.creationDate())
                        .ifPresent(farm::setCreationDate);
        Optional.ofNullable(updateFarmDto.surface())
                .ifPresent(farm::setSurface);

//        farm.setName(updateFarmDto.name());
//        farm.setLocalization(updateFarmDto.localization());
//        farm.setCreationDate(updateFarmDto.creationDate());
//        farm.setSurface(farm.getSurface());
        Farm updatedFarm = farmRepository.save(farm);
        return farmMapper.toDto(updatedFarm);
    }

    @Override
    public List<FarmDto> findAll(Integer pageNumber, Integer size) {
        PageRequest pageable = PageRequest.of(pageNumber, size);
        return farmRepository.findAll(pageable).stream()
                .map(farmMapper::toDto)
                .toList();
    }

    @Override
    public void delete(Long id) {
        Farm farm = farmRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Farm", id));
        farmRepository.delete(farm);
    }

    @Override
    public List<FarmDto> search(String name, String localization, Double surface, LocalDate creationDate) {
        return farmRepository.search(name, localization, surface,creationDate).stream()
                .map(farmMapper::toDto)
                .toList();
    }

    @Override
    public Farm getFarmEntityById(Long id) {
        return farmRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Farm", id));
    }


}
