package com.eagrigorieva.parser;

import com.eagrigorieva.enumeration.Command;
import lombok.extern.log4j.Log4j2;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2
public class OperationParser {

    public UserInput getParseOperation(String inputString) {
        if (inputString == null) {
            return new UserInput(Command.INCORRECT, Collections.emptyList());
        }
        List<String> listStrings = Arrays.stream(inputString.split(" "))
                .map(String::trim)
                .collect(Collectors.toList());

        if (listStrings.isEmpty()) {
            return new UserInput(Command.INCORRECT, Collections.emptyList());
        }

        Command command = Command.getCommand(listStrings.get(0));
        List<String> args = listStrings.subList(1, listStrings.size());
        return new UserInput(command, args);
    }
}
