package com.wora.citronix.controllers;

import com.wora.citronix.models.DTOs.saleDtos.CreateSaleDto;
import com.wora.citronix.models.DTOs.saleDtos.SaleDto;
import com.wora.citronix.models.DTOs.saleDtos.UpdateSaleDto;
import com.wora.citronix.services.inter.ISaleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class SaleController {
    private final ISaleService saleService;

    @PostMapping("/sales")
    public ResponseEntity<SaleDto> create(@RequestBody @Valid CreateSaleDto createSaleDto){
        return new ResponseEntity<>(saleService.save(createSaleDto), HttpStatus.CREATED);
    }

    @GetMapping("/sales")
    public ResponseEntity<List<SaleDto>> findAll(@RequestParam int pageNumber, @RequestParam int size){
        List<SaleDto> sales = saleService.findAll(pageNumber, size);
        return new ResponseEntity<>(sales, HttpStatus.OK);
    }

    @GetMapping("/sale/{id}")
    public ResponseEntity<SaleDto> findById(@PathVariable Long id){
        return new ResponseEntity<>(saleService.findById(id), HttpStatus.OK);
    }

    @PutMapping("/sale/{id}")
    public ResponseEntity<SaleDto> update(@RequestBody @Valid UpdateSaleDto updateSaleDto, @PathVariable Long id){
        return new ResponseEntity<>(saleService.update(updateSaleDto, id), HttpStatus.OK);
    }


}
