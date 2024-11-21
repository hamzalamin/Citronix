package com.wora.citronix.repositories;

import com.wora.citronix.models.entities.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleRepository extends JpaRepository<Sale, Long> {
}
