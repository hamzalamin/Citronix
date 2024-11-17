package com.wora.citronix.controllers;

import com.wora.citronix.models.DTOs.farmDtos.CreateFarmDto;
import com.wora.citronix.models.DTOs.farmDtos.FarmDto;
import com.wora.citronix.services.inter.IFarmService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class FarmController {
    private final IFarmService farmService;

    @PostMapping("/farms")
    public ResponseEntity<FarmDto> create(@RequestBody @Valid CreateFarmDto farmDto){
        return new ResponseEntity<>(farmService.save(farmDto), HttpStatus.CREATED);
    }

}
