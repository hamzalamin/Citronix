package com.wora.citronix.services.impl;

import com.wora.citronix.exceptions.EntityNotFoundException;
import com.wora.citronix.exceptions.InsufficientFieldSurfaceException;
import com.wora.citronix.exceptions.PlantingDateException;
import com.wora.citronix.mappers.TreeMapper;
import com.wora.citronix.models.DTOs.treeDtos.CreateTreeDto;
import com.wora.citronix.models.DTOs.treeDtos.TreeDto;
import com.wora.citronix.models.DTOs.treeDtos.UpdateTreeDto;
import com.wora.citronix.models.entities.Field;
import com.wora.citronix.models.entities.Tree;
import com.wora.citronix.repositories.TreeRepository;
import com.wora.citronix.services.inter.IFieldService;
import com.wora.citronix.services.inter.ITreeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TreeService implements ITreeService {

    private final TreeRepository treeRepository;
    private final TreeMapper treeMapper;
    private final IFieldService fieldService;

    @Override
    public TreeDto save(CreateTreeDto createTreeDto) {
        Tree tree = treeMapper.toEntity(createTreeDto);

        Field field = fieldService.getFieldEntityById(createTreeDto.fieldId());

        if (tree.getPlantingDate().isBefore(field.getFarm().getCreationDate())){
            throw new PlantingDateException("The planting date must bee must bee after the creation date of farm.");
        }
        if (!isBetweenFiveAndSevenMonths(tree)) {
            throw new PlantingDateException("The planting date must fall within a range of 5 to 7 months from the current date.");
        }

        int treeCount = treeRepository.countByFieldId(field.getId());
        double treePerHectare = (treeCount + 1) / field.getSurface();
        double maxAllowedTrees = field.getSurface() * 100;

        if (treePerHectare > 100) {
            throw new InsufficientFieldSurfaceException(
                    "The number of trees exceeds the allowable limit for this field. Maximum allowable trees for this surface area: "
                            + maxAllowedTrees + ".");
        }

        tree.setField(field);
        Tree savedTree = treeRepository.save(tree);
        return treeMapper.toDto(savedTree);
    }

    @Override
    public TreeDto findById(Long id) {
        Tree tree = treeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tree", id));
        return treeMapper.toDto(tree);
    }

    @Override
    public TreeDto update(UpdateTreeDto updateTreeDto, Long id) {
        Tree tree = treeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tree", id));

        Field field = fieldService.getFieldEntityById(updateTreeDto.fieldId());

        if (!isBetweenFiveAndSevenMonths(tree)) {
            throw new PlantingDateException("The planting date must fall within a range of 5 to 7 months from the current date.");
        }
        int treeCount = treeRepository.countByFieldId(field.getId());
        double treePerHectare = (treeCount + 1) / field.getSurface();
        double maxAllowedTrees = field.getSurface() * 100;

        if (treePerHectare > 100) {
            throw new InsufficientFieldSurfaceException(
                    "The number of trees exceeds the allowable limit for this field. Maximum allowable trees for this surface area: "
                            + maxAllowedTrees + ".");
        }

        tree.setField(field);
        tree.setPlantingDate(updateTreeDto.plantingDate());
        Tree savedTree = treeRepository.save(tree);
        return treeMapper.toDto(savedTree);
    }

    @Override
    public List<TreeDto> findAll(Integer pageNumber, Integer size) {
        PageRequest pageable = PageRequest.of(pageNumber, size);
        return treeRepository.findAll(pageable).stream()
                .map(treeMapper::toDto)
                .toList();
    }

    @Override
    public void delete(Long id) {
        Tree tree = treeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tree", id));
        treeRepository.delete(tree);
    }

    public boolean isBetweenFiveAndSevenMonths(Tree tree) {
        LocalDate plantingDate = tree.getPlantingDate();
        LocalDate currentDate = LocalDate.now();
        int monthsDifference = Period.between(plantingDate, currentDate).getMonths();
        return monthsDifference >= 5 && monthsDifference <= 7;
    }
}
