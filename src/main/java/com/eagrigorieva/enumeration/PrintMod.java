package com.eagrigorieva.enumeration;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum PrintMod {
    ALL,
    CREATED,
    COMPLETED;

    public static PrintMod getPrintMod(String modStr) {
        if (modStr.equals("default")) {
            return CREATED;
        }

        for (PrintMod mod : values()) {
            if (mod.name().equalsIgnoreCase(modStr)) {
                return mod;
            }
        }
        return ALL;
    }
}
