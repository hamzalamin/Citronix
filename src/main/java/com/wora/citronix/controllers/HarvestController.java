package com.wora.citronix.controllers;

import com.wora.citronix.models.DTOs.harvestDtos.CreateHarvestDto;
import com.wora.citronix.models.DTOs.harvestDtos.HarvestDto;
import com.wora.citronix.services.inter.IHarvestService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
@RequiredArgsConstructor
public class HarvestController {
    private final IHarvestService harvestService;

    @PostMapping("/harvests")
    public ResponseEntity<HarvestDto> create(@RequestBody @Valid CreateHarvestDto createHarvestDto){
        return new ResponseEntity<>(harvestService.save(createHarvestDto), HttpStatus.CREATED);
    }

    @GetMapping("/harvests")
    public ResponseEntity<List<HarvestDto>> findAll(@RequestParam int pageNumber, @RequestParam int size){
        List<HarvestDto> harvests = harvestService.findAll(pageNumber, size);
        return new ResponseEntity<>(harvests, HttpStatus.OK);
    }

}
