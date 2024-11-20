package com.wora.citronix.controllers;

import com.wora.citronix.models.DTOs.harvestDetailDtos.CreateHarvestDetailsDto;
import com.wora.citronix.models.DTOs.harvestDetailDtos.HarvestDetailsDto;
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

}
