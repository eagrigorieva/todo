package com.eagrigorieva.controller;

import com.eagrigorieva.dto.CreateRequestUserDto;
import com.eagrigorieva.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Validated
@Secured("ROLE_ADMIN")
@RestController
@RequestMapping("users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@Valid @RequestBody CreateRequestUserDto request , Authentication authentication) {
        userService.create(request.getPassword(), request.getUsername(), request.getRole());
    }

    @DeleteMapping("/{id}")
    public void delete(@NotNull @PathVariable(value = "id") Long id) {
        userService.delete(id);
    }
}
