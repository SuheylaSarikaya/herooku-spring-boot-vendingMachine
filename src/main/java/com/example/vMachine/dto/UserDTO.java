package com.example.vMachine.dto;

import com.sun.istack.NotNull;
import lombok.*;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class UserDTO {

    @NotNull
    private String username;
    @NotNull
    private String password;
}
