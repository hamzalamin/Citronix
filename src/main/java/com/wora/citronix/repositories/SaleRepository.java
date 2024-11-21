package com.wora.citronix.repositories;

import com.wora.citronix.models.entities.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SaleRepository extends JpaRepository<Sale, Long> {
    @Query("SELECT SUM (e.saleQuantity) FROM sales e WHERE e.harvest.id = :harvestId")
    Double getSumSoldQuantityByHarvestId(Long harvestId);
}
