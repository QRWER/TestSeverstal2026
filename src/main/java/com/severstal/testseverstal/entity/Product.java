package com.severstal.testseverstal.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Date;

@Entity
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int id;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Type is required")
    private TypeOfProduct type;

    @Column
    @NotNull(message = "Date is required")
    private Date date;

    @Column
    @NotNull(message = "Cost is required")
    private double cost;

    @Column
    @NotNull(message = "Weight is required")
    private int weight;

    @ManyToOne
    @JoinColumn(name = "provider_id")
    @JsonBackReference("provider-products")
    private Provider provider;

}
