package com.eagrigorieva.parser;

import com.eagrigorieva.enumeration.Command;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
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
