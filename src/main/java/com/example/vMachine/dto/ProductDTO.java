package com.example.vMachine.dto;

import com.sun.istack.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class ProductDTO {
    @NotNull
    private Long id;
    @NotNull
    private int count;
    @NotNull
    private String name;
    @NotNull
    private String img;
    @NotNull
    private Double price;
    @NotNull
    private int temperature;

}
