package com.wora.citronix.repositories;

import com.wora.citronix.models.entities.Field;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FieldRepository extends JpaRepository<Field, Long> {
}
