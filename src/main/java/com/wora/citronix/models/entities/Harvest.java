package com.wora.citronix.models.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity(name = "harvests")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Harvest {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(mappedBy = "harvest", cascade = CascadeType.ALL)
    private List<HarvestDetails> harvestDetails;
}
