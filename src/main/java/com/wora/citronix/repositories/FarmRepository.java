package com.wora.citronix.repositories;

import com.wora.citronix.models.entities.Farm;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FarmRepository extends JpaRepository<Farm, Long> {
}
