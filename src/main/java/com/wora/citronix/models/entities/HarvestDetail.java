package com.wora.citronix.models.entities;

import com.wora.citronix.models.entities.embeddables.HarvestDetailsId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "harvest_details")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class HarvestDetail {
    @EmbeddedId
    private HarvestDetailsId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "harvest_id", insertable = false, updatable = false)
    private Harvest harvest;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tree_id", insertable = false, updatable = false)
    private Tree tree;
}
