package com.wora.citronix.controllers;

import com.wora.citronix.models.DTOs.treeDtos.CreateTreeDto;
import com.wora.citronix.models.DTOs.treeDtos.TreeDto;
import com.wora.citronix.services.inter.ITreeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1")
@RequiredArgsConstructor
public class TreeController {
    private ITreeService treeService;

    @PostMapping("/Trees")
    public ResponseEntity<TreeDto> create(CreateTreeDto createTreeDto){
        return new ResponseEntity<>(treeService.save(createTreeDto), HttpStatus.CREATED);
    }
}
