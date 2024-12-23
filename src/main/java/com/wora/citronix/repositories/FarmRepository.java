package com.wora.citronix.repositories;

import com.wora.citronix.models.entities.Farm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FarmRepository extends JpaRepository<Farm, Long>, FarmRepositoryCustom {
}
