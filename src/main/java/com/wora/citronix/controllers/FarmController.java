package com.wora.citronix.controllers;

import com.wora.citronix.models.DTOs.farmDtos.CreateFarmDto;
import com.wora.citronix.models.DTOs.farmDtos.FarmDto;
import com.wora.citronix.models.DTOs.farmDtos.UpdateFarmDto;
import com.wora.citronix.services.inter.IFarmService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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

    @PutMapping("/farm/{id}")
    public ResponseEntity<FarmDto> update(
            @RequestBody @Valid UpdateFarmDto updateFarmDto,
            @PathVariable Long id
    ){
        return new ResponseEntity<>(farmService.update(updateFarmDto, id), HttpStatus.OK);
    }

    @DeleteMapping("/farm/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        farmService.delete(id);
        return new ResponseEntity<>("FARM WITH ID: " + id + " DELETED SUCCESSFULLY !!", HttpStatus.OK);
    }

    @GetMapping("/search/farms")
    public ResponseEntity<List<FarmDto>> search(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "localization", required = false) String localization,
            @RequestParam(value = "creationDate", required = false) LocalDate creationDate,
            @RequestParam(value = "surface", required = false) Double surface
            ){
        List<FarmDto> farms = farmService.search(name, localization, surface, creationDate);
        return new ResponseEntity<>(farms, HttpStatus.OK);
    }

}
