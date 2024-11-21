package com.wora.citronix.services.impl;

import com.wora.citronix.exceptions.EntityNotFoundException;
import com.wora.citronix.exceptions.PlantingDateException;
import com.wora.citronix.mappers.HarvestDetailsMapper;
import com.wora.citronix.models.DTOs.harvestDetailDtos.CreateHarvestDetailsDto;
import com.wora.citronix.models.DTOs.harvestDetailDtos.HarvestDetailsDto;
import com.wora.citronix.models.DTOs.harvestDetailDtos.UpdateHarvestDetailsDto;
import com.wora.citronix.models.entities.Harvest;
import com.wora.citronix.models.entities.HarvestDetail;
import com.wora.citronix.models.entities.Tree;
import com.wora.citronix.models.entities.embeddables.HarvestDetailsId;
import com.wora.citronix.models.enumes.Season;
import com.wora.citronix.repositories.HarvestDetailsRepository;
import com.wora.citronix.services.inter.IHarvestDetailService;
import com.wora.citronix.services.inter.IHarvestService;
import com.wora.citronix.services.inter.ITreeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HarvestDetailService implements IHarvestDetailService {
    private final HarvestDetailsRepository harvestDetailsRepository;
    private final HarvestDetailsMapper harvestDetailsMapper;
    private final IHarvestService harvestService;
    private final ITreeService treeService;

    @Override
    public HarvestDetailsDto save(CreateHarvestDetailsDto createHarvestDetailsDto) {
        Long treeId = createHarvestDetailsDto.treeId();
        Long harvestId = createHarvestDetailsDto.harvestId();

        Harvest harvest = harvestService.findEntityById(harvestId);
        Tree tree = treeService.findTreeById(treeId);
        Season harvestSeason = harvest.getSeason();

        boolean exists = harvestDetailsRepository.existsByTreeIdAndHarvestSeason(treeId, harvestSeason);
        if (exists) {
            throw new PlantingDateException("This tree has already been harvested in the given season.");
        }

        HarvestDetail harvestDetail = new HarvestDetail(harvest, tree, createHarvestDetailsDto.quantity());
        HarvestDetail createdHarvestDetail = harvestDetailsRepository.save(harvestDetail);
        return harvestDetailsMapper.toDto(createdHarvestDetail);
    }

    @Override
    public HarvestDetailsDto findById(HarvestDetailsId id) {
        HarvestDetail harvestDetail = harvestDetailsRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Harvest Details", id));
        return harvestDetailsMapper.toDto(harvestDetail);
    }

    @Override
    public HarvestDetailsDto update(UpdateHarvestDetailsDto updateHarvestDetailsDto, HarvestDetailsId id) {
        HarvestDetail harvestDetail = harvestDetailsRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Harvest Details", id));
        Long treeId = updateHarvestDetailsDto.treeId();
        Long harvestId = updateHarvestDetailsDto.harvestId();

        Harvest harvest = harvestService.findEntityById(harvestId);
        Tree tree = treeService.findTreeById(treeId);

        harvestDetail.setId(id);
        harvestDetail.setHarvest(harvest);
        harvestDetail.setTree(tree);
        harvestDetail.setQuantity(updateHarvestDetailsDto.quantity());
        HarvestDetail createdHarvestDetail = harvestDetailsRepository.save(harvestDetail);
        return harvestDetailsMapper.toDto(createdHarvestDetail);
    }

    @Override
    public List<HarvestDetailsDto> findAll(Integer pageNumber, Integer size) {
        PageRequest pageable = PageRequest.of(pageNumber, size);
        return harvestDetailsRepository.findAll(pageable).stream()
                .map(harvestDetailsMapper::toDto)
                .toList();
    }

    @Override
    public void delete(HarvestDetailsId id) {
        HarvestDetail harvestDetail = harvestDetailsRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Harvest Details", id));
        harvestDetailsRepository.delete(harvestDetail);
    }
}
