package com.eagrigorieva.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateRequestUserDto {
    private String username;
    private String password;
    private String role;
}
