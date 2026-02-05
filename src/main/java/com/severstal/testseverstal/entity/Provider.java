package com.severstal.testseverstal.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Provider {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    @NotNull
    private String name;

    @OneToMany(mappedBy = "provider",
                fetch = FetchType.EAGER)
    @JsonManagedReference("provider-products")
    private List<Product> products;

    @OneToMany(mappedBy = "provider",
                fetch = FetchType.EAGER)
    private List<Supply> supplies;

}
