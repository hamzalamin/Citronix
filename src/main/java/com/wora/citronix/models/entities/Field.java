package com.wora.citronix.models.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity(name = "fileds")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Field {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name", unique = true)
    @NotBlank
    private String name;

    @Column(name = "surface")
    @NotNull
    @Positive
    private Double surface;

    @ManyToOne
    @JoinColumn(name = "farm_id")
    private Farm farm;

    @OneToMany(mappedBy = "field", cascade = CascadeType.ALL)
    private List<Tree> trees;
}
