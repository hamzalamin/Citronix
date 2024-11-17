package com.wora.citronix.repositories;

import com.wora.citronix.models.entities.Farm;

import java.time.LocalDate;
import java.util.List;

public interface CriteriaAPI {
    List<Farm> searchFarms(String name, String localization, Double surface, LocalDate creationDate);
}
