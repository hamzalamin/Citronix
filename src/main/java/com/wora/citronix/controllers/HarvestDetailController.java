package com.wora.citronix.controllers;

import com.wora.citronix.models.DTOs.harvestDetailDtos.CreateHarvestDetailsDto;
import com.wora.citronix.models.DTOs.harvestDetailDtos.HarvestDetailsDto;
import com.wora.citronix.models.DTOs.harvestDetailDtos.UpdateHarvestDetailsDto;
import com.wora.citronix.models.entities.embeddables.HarvestDetailsId;
import com.wora.citronix.services.inter.IHarvestDetailService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api/harvest/details")
@RequiredArgsConstructor
public class HarvestDetailController {
    private final IHarvestDetailService harvestDetailService;

    @PostMapping
    public ResponseEntity<HarvestDetailsDto> create(@RequestBody @Valid CreateHarvestDetailsDto createHarvestDetailsDto){
        return new ResponseEntity<>(harvestDetailService.save(createHarvestDetailsDto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<HarvestDetailsDto>> findAll(@RequestParam int pageNumber, @RequestParam int size){
        List<HarvestDetailsDto> hds = harvestDetailService.findAll(pageNumber, size);
        return new ResponseEntity<>(hds, HttpStatus.OK);
    }

    @GetMapping("/harvest/{harvestId}/tree/{treeId}")
    public ResponseEntity<HarvestDetailsDto> findById(@PathVariable Long harvestId, @PathVariable Long treeId){
        HarvestDetailsId id = new HarvestDetailsId(treeId, harvestId);
        return new ResponseEntity<>(harvestDetailService.findById(id), HttpStatus.OK);
    }

    @PutMapping("/harvest/{harvestId}/tree/{treeId}")
    public ResponseEntity<HarvestDetailsDto> update(@RequestBody @Valid UpdateHarvestDetailsDto updateHarvestDetailsDto, @PathVariable Long harvestId, @PathVariable Long treeId){
        HarvestDetailsId id = new HarvestDetailsId(treeId, harvestId);
        return new ResponseEntity<>(harvestDetailService.update(updateHarvestDetailsDto, id), HttpStatus.OK);
    }

    @DeleteMapping("/harvest/{harvestId}/tree/{treeId}")
    public ResponseEntity<String> delete(@PathVariable Long harvestId, @PathVariable Long treeId){
        HarvestDetailsId id = new HarvestDetailsId(treeId, harvestId);
        harvestDetailService.delete(id);
        return new ResponseEntity<>("Harvest details WITH ID: " + id + " DELETED SUCCESSFULLY !!", HttpStatus.OK);
    }

}
