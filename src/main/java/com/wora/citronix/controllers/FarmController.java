package com.wora.citronix.controllers;

import com.wora.citronix.models.DTOs.farmDtos.CreateFarmDto;
import com.wora.citronix.models.DTOs.farmDtos.FarmDto;
import com.wora.citronix.services.inter.IFarmService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class FarmController {
    private final IFarmService farmService;

    @PostMapping("/farms")
    public ResponseEntity<FarmDto> create(@RequestBody @Valid CreateFarmDto farmDto){
        return new ResponseEntity<>(farmService.save(farmDto), HttpStatus.CREATED);
    }

    @GetMapping("/farms")
    public ResponseEntity<List<FarmDto>> findAll(
            @RequestParam Integer pageNumber,
            @RequestParam Integer size
    ){
        List<FarmDto> farms = farmService.findAll(pageNumber, size);
        return new ResponseEntity<>(farms, HttpStatus.OK);
    }

    @GetMapping("/farm/{id}")
    public ResponseEntity<FarmDto> findAById(@PathVariable Long id){
        return new ResponseEntity<>(farmService.findById(id), HttpStatus.OK);
    }

}
