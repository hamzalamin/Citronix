package com.wora.citronix.repositories;

import com.wora.citronix.models.entities.HarvestDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HarvestDetailsRepository extends JpaRepository<HarvestDetail, Long> {
}
