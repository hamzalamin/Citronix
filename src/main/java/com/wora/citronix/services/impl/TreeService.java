package com.wora.citronix.services.impl;

import com.wora.citronix.models.DTOs.treeDtos.CreateTreeDto;
import com.wora.citronix.models.DTOs.treeDtos.TreeDto;
import com.wora.citronix.models.DTOs.treeDtos.UpdateTreeDto;
import com.wora.citronix.services.inter.ITreeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TreeService implements ITreeService {
    @Override
    public TreeDto save(CreateTreeDto createTreeDto) {
        return null;
    }

    @Override
    public TreeDto findById(Long aLong) {
        return null;
    }

    @Override
    public TreeDto update(UpdateTreeDto updateTreeDto, Long aLong) {
        return null;
    }

    @Override
    public List<TreeDto> findAll(Integer pageNumber, Integer size) {
        return List.of();
    }

    @Override
    public void delete(Long aLong) {

    }
}
