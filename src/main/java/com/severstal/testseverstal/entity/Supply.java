package com.severstal.testseverstal.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Entity
@Data
public class Supply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    @NotNull(message = "Date is required")
    private Date date;

    @Column
    private int totalWeight;

    @Column
    private double totalCost;

    @ManyToOne
    @JoinColumn(name = "provider_id")
    @JsonIgnore
    private Provider provider;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Product> products;

    public Supply(Date date, Provider provider, List<Product> products) {
        this.date = date;
        this.totalWeight = 0;
        this.totalCost = 0;
        for (Product product : products) {
            this.totalWeight += product.getWeight();
            this.totalCost += product.getCost();
        }
        this.provider = provider;
        this.products = products;
    }

    public Supply() {
        super();
    }
}
