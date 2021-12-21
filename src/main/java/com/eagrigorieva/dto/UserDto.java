package com.eagrigorieva.dto;

import com.eagrigorieva.model.UserRole;
import lombok.Data;

@Data
public class UserDto {
    private Long id;
    private String username;
    private String password;
    private UserRole role;
}
