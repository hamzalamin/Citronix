package com.wora.citronix.controllers;

import com.wora.citronix.models.DTOs.saleDtos.CreateSaleDto;
import com.wora.citronix.models.DTOs.saleDtos.SaleDto;
import com.wora.citronix.services.inter.ISaleService;
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
public class SaleController {
    private final ISaleService saleService;

    @PostMapping("/sals")
    public ResponseEntity<SaleDto> create(@RequestBody @Valid CreateSaleDto createSaleDto){
        return new ResponseEntity<>(saleService.save(createSaleDto), HttpStatus.CREATED);
    }


}
