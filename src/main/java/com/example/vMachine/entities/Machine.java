package com.example.vMachine.entities;


import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.*;
@Entity
@Table(name="machine")
@Data
public class Machine {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    private double totalBalance;
}
