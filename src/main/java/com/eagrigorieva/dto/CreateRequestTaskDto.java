package com.eagrigorieva.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;

@Getter
@Setter
public class CreateRequestTaskDto {

    @NotEmpty
    @Size(min = 2, max = 100)
    private String description;
}
