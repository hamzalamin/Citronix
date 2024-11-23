package com.wora.citronix.models.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "farms")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Farm {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name", unique = true)
    @NotBlank
    private String name;

    @Column(name = "localization", unique = true)
    @NotBlank
    private String localization;

    @Column(name = "surface")
    @NotNull @Positive
    private Double surface;

    @Column(name = "creation_date")
    @NotNull
    private LocalDate creationDate;

    @OneToMany(mappedBy = "farm", cascade = CascadeType.ALL)
    private List<Field> fields = new ArrayList<>();
}
