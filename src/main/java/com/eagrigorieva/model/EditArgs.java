package com.eagrigorieva.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EditArgs {
    private int id;
    private String description;
}
