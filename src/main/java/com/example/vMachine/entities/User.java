package com.example.vMachine.entities;


import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.*;
@Entity
@Table(name="user")
@Data
public class User {
    public User() {

    }
    @Id
    @NotNull
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String username;
    @NotNull
    private String password;


}
