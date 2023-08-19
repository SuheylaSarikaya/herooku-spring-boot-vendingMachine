package com.example.vMachine.entities;


import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="products")
@Data
public class Product {
    public Product() {
    }
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @NotNull
    private Long id;
    @NotNull
    private int count;
    @NotNull
    private String name;
    @NotNull
    private Double price;
    @NotNull
    private int temperature;

    private String img;
    @NotNull
    private int capacity;


}
