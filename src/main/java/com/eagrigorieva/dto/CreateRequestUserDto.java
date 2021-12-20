package com.eagrigorieva.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;

@Getter
@Setter
public class CreateRequestUserDto {
    @NotEmpty
    @Size(min = 2, max = 20)
    private String username;

    @NotEmpty
    @Size(min = 3, max = 20)
    private String password;

    @NotEmpty
    private String role;
}
