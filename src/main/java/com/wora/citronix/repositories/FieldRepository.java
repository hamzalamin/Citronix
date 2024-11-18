package com.wora.citronix.repositories;

import com.wora.citronix.models.entities.Farm;
import com.wora.citronix.models.entities.Field;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FieldRepository extends JpaRepository<Field, Long> {
    List<Field> findByFarm(Farm farm);
}
