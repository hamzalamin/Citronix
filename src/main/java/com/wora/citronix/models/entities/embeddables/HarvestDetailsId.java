package com.wora.citronix.models.entities.embeddables;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Positive;

@Embeddable
public record HarvestDetailsId(
        @Column(name = "tree_id")
        @Positive
        Long treeId,

        @Column(name = "harvest_id")
        @Positive
        Long harvestId
) {
}
