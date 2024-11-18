package com.wora.citronix.controllers;

import com.wora.citronix.models.DTOs.fieldDtos.CreateFieldDto;
import com.wora.citronix.models.DTOs.fieldDtos.FieldDto;
import com.wora.citronix.services.inter.IFieldService;
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
public class FieldControllers {
    private final IFieldService fieldService;

    @PostMapping("/fields")
    public ResponseEntity<FieldDto> create(@RequestBody CreateFieldDto createFieldDto){
        return new ResponseEntity<>(fieldService.save(createFieldDto), HttpStatus.CREATED);
    }
}
