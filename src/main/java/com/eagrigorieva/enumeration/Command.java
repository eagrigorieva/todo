package com.eagrigorieva.enumeration;

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

    public static Command getCommand(String commandStr) {
        for (Command command : values()) {
            if (command.name().equalsIgnoreCase(commandStr)) {
                return command;
            }
        }
        return INCORRECT;
    }
}
