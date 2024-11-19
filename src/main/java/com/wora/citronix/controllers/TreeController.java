package com.wora.citronix.controllers;

import com.wora.citronix.models.DTOs.treeDtos.CreateTreeDto;
import com.wora.citronix.models.DTOs.treeDtos.TreeDto;
import com.wora.citronix.models.DTOs.treeDtos.UpdateTreeDto;
import com.wora.citronix.services.inter.ITreeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
@RequiredArgsConstructor
public class TreeController {
    private final ITreeService treeService;

    @PostMapping("/trees")
    public ResponseEntity<TreeDto> create(@RequestBody @Valid CreateTreeDto createTreeDto){
        return new ResponseEntity<>(treeService.save(createTreeDto), HttpStatus.CREATED);
    }

    @GetMapping("/tree/{id}")
    public ResponseEntity<TreeDto> findById(@PathVariable Long id){
        return new ResponseEntity<>(treeService.findById(id), HttpStatus.OK);
    }

    @GetMapping("/trees")
    public ResponseEntity<List<TreeDto>> findAll(@RequestParam int pageNumber, int size){
        List<TreeDto> trees = treeService.findAll(pageNumber, size);
        return new ResponseEntity<>(trees, HttpStatus.OK);
    }

    @PutMapping("/tree/{id}")
    public ResponseEntity<TreeDto> update(
            @RequestBody UpdateTreeDto updateTreeDto,
            @PathVariable Long id
    ){
        return new ResponseEntity<>(treeService.update(updateTreeDto ,id), HttpStatus.OK);
    }

}
