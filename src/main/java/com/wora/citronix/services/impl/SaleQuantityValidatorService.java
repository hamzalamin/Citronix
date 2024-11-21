package com.wora.citronix.services.impl;

import com.wora.citronix.exceptions.PlantingDateException;
import com.wora.citronix.repositories.HarvestDetailsRepository;
import com.wora.citronix.repositories.SaleRepository;
import com.wora.citronix.services.inter.ISaleQuantityValidatorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SaleQuantityValidatorService implements ISaleQuantityValidatorService {
    private final SaleRepository saleRepository;
    private final HarvestDetailsRepository harvestDetailsRepository;

    @Override
    public void ensureWantedQuantityExist(Double wantedQuantity, Long harvestId) {
        Double harvestQuantity = harvestDetailsRepository.getSumQuantityByHarvestId(harvestId);
        Double soldQuantity = Optional.ofNullable(saleRepository.getSumSoldQuantityByHarvestId(harvestId))
                .orElse(0.0);

        Double remainingQuantity = harvestQuantity - soldQuantity ;

        if (remainingQuantity - wantedQuantity < 0){
            throw new PlantingDateException("the wanted quantity does not exist");
        }

    }
}
