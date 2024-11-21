package com.wora.citronix.models.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Entity(name = "sales")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "client_name")
    @NotBlank
    private String clientName;

    @Column(name = "unit_price")
    @NotNull
    private Double unitPrice;

    @Column(name = "sale_date")
    @NotNull
    private LocalDate SaleDate;

}
