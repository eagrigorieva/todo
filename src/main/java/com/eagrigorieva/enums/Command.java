package com.eagrigorieva.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Command {
    CREATE,
    PRINT,
    TOGGLE,
    QUIT,
    INCORRECT;

    public String toLowerCase(){
        return name().toLowerCase();
    }


}
