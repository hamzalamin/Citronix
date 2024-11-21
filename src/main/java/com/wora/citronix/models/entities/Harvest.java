package com.wora.citronix.models.entities;


import com.wora.citronix.models.enumes.Season;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
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

    @Column(name = "creation_date")
    @NotNull
    private LocalDate creationDate;

    @Column(name = "season")
    @NotNull
    private Season season;

    @OneToMany(mappedBy = "harvest", cascade = CascadeType.ALL)
    private List<HarvestDetail> harvestDetails;


    @OneToMany(mappedBy = "harvest", cascade = CascadeType.ALL)
    private List<Sale> sales;
}
