package com.wora.citronix.repositories;

import com.wora.citronix.models.entities.HarvestDetail;
import com.wora.citronix.models.entities.embeddables.HarvestDetailsId;
import com.wora.citronix.models.enumes.Season;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface HarvestDetailsRepository extends JpaRepository<HarvestDetail, HarvestDetailsId> {
    boolean existsByTreeIdAndHarvestSeason(Long treeId, Season harvestSeason);

    @Query("SELECT SUM (e.quantity) FROM harvest_details e WHERE e.id.harvestId = :harvestId")
    Double getSumQuantityByHarvestId(Long harvestId);
}
