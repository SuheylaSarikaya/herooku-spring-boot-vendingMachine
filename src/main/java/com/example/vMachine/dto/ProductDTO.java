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

    private int count;

    private String name;

    private String img;

    private int price;

    private int temperature;

}
