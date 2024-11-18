package com.wora.citronix.controllers;

import com.wora.citronix.models.DTOs.fieldDtos.CreateFieldDto;
import com.wora.citronix.models.DTOs.fieldDtos.FieldDto;
import com.wora.citronix.models.DTOs.fieldDtos.UpdateFieldDto;
import com.wora.citronix.services.inter.IFieldService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class FieldControllers {
    private final IFieldService fieldService;

    @PostMapping("/fields")
    public ResponseEntity<FieldDto> create(@RequestBody @Valid CreateFieldDto createFieldDto){
        System.out.println("yo id farm : " + createFieldDto.farmId());
        return new ResponseEntity<>(fieldService.save(createFieldDto), HttpStatus.CREATED);
    }

    @GetMapping("/fields")
    public ResponseEntity<List<FieldDto>> findAll(
            @RequestParam Integer pageNumber,
            @RequestParam Integer size
    ){
        List<FieldDto> fields = fieldService.findAll(pageNumber, size);
        return new ResponseEntity<>(fields, HttpStatus.CREATED);
    }

    @GetMapping("/field/{id}")
    public ResponseEntity<FieldDto> findById(@PathVariable Long id){
        return new ResponseEntity<>(fieldService.findById(id), HttpStatus.OK);
    }

    @PutMapping("/field/{id}")
    public ResponseEntity<FieldDto> update(@RequestBody UpdateFieldDto updateFieldDto, @PathVariable Long id){
        return new ResponseEntity<>(fieldService.update(updateFieldDto, id), HttpStatus.CREATED);
    }
}
