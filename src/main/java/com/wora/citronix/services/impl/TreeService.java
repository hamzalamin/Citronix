package com.wora.citronix.services.impl;

import com.wora.citronix.exceptions.InsufficientFarmSurfaceException;
import com.wora.citronix.exceptions.InsufficientFieldSurfaceException;
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
import org.springframework.stereotype.Service;

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

        Field field = fieldService.getFieldEntityById(createTreeDto.field());
        if (field.getSurface() == 1){
            int treeCount = treeRepository.countByFieldId(field.getId());
            if (treeCount >= 100){
                throw new InsufficientFieldSurfaceException("Maximum number of trees (100) exceeded for this field.");
            }
        }
        Tree savedTree = treeRepository.save(tree);
        return treeMapper.toDto(savedTree);
    }

    @Override
    public TreeDto findById(Long id) {
        return null;
    }

    @Override
    public TreeDto update(UpdateTreeDto updateTreeDto, Long id) {
        return null;
    }

    @Override
    public List<TreeDto> findAll(Integer pageNumber, Integer size) {
        return List.of();
    }

    @Override
    public void delete(Long id) {

    }
}
