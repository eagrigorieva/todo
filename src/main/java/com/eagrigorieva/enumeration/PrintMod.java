package com.eagrigorieva.enumeration;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum PrintMod {
    ALL,
    CREATED,
    COMPLETED;

    public static PrintMod getPrintMod(String modStr) {
        for (PrintMod mod : values()) {
            if (mod.name().equalsIgnoreCase(modStr)) {
                return mod;
            }
        }
        return CREATED;
    }
}
