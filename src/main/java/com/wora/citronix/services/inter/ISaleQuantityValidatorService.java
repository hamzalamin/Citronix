package com.wora.citronix.services.inter;

public interface ISaleQuantityValidatorService {
    void ensureWantedQuantityExist(Double wantedQuantity, Long harvestId);
}
