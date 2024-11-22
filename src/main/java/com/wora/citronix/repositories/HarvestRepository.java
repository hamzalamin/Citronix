package com.wora.citronix.repositories;

import com.wora.citronix.models.entities.Harvest;
import com.wora.citronix.models.enumes.Season;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface HarvestRepository extends JpaRepository<Harvest, Long> {
    boolean existsByFieldIdAndSeasonAndCreationDate(Long fieldId, Season season, LocalDate creationDate);
}
