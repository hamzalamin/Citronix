package com.wora.citronix.repositories;

import com.wora.citronix.models.entities.Farm;
import com.wora.citronix.models.entities.Field;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FieldRepository extends JpaRepository<Field, Long> {
    List<Field> findByFarm(Farm farm);
}
