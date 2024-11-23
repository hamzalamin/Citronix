package com.wora.citronix.models.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "trees")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Tree {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "planting_date")
    @NotNull
    private LocalDate plantingDate;

    @ManyToOne
    @JoinColumn(name = "field_id")
    private Field field;

    @OneToMany(mappedBy = "tree", cascade = CascadeType.ALL)
    private List<HarvestDetail> harvestDetails = new ArrayList<>();

    public int getAge(){
        return Period.between(plantingDate, LocalDate.now()).getYears();
    }

    public double getProductivityByKg(){
        int age = getAge();
        if (age <= 3){
            return 2.5;
        }
        if (age > 3 && age <= 10){
            return 12;
        }
        if (age > 10 && age <=20){
            return 20;
        }
        if (age > 20){
            return 0;
        }
        return 0;
    }

}
