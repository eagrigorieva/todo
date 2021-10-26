package com.eagrigorieva.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Command {
    ADD,
    PRINT,
    TOGGLE,
    SEARCH,
    DELETE,
    EDIT,
    QUIT,
    INCORRECT;

    public String toLowerCase(){
        return name().toLowerCase();
    }


}
