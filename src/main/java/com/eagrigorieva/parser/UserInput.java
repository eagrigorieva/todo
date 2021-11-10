package com.eagrigorieva.parser;

import com.eagrigorieva.enumeration.Command;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class UserInput {
    private Command command;
    private List<String> argList;
}
