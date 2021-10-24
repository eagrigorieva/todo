package com.eagrigorieva.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum PrintMod {
    ALL,
    CREATED,
    COMPLETED;

    public String toLowerCase(){
        return name().toLowerCase();
    }
}
