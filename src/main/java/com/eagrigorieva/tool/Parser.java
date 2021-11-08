package com.eagrigorieva.tool;

import com.eagrigorieva.model.EditArgs;
import lombok.extern.log4j.Log4j2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2
public class Parser {

    public int parseStrToInt(String inputString){
        if (inputString.matches("\\d+")) {
            return Integer.parseInt(inputString);
        }
        return -1;
    }

    public EditArgs parseEditCommand(String argsStr) {
        List<String> argsList = Arrays.stream(argsStr.split(" "))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .collect(Collectors.toList());
        if (argsList.size() == 1) {
            return new EditArgs(parseStrToInt(argsList.get(0)), null);
        }
        if (argsList.size() != 2) {
            return new EditArgs(-1, null);
        }
        return new EditArgs(parseStrToInt(argsList.get(0)), argsList.get(1));
    }

    public List<String> parseInputLine(String inputCommand) {
        List<String> inputStrList = new ArrayList<>();

        String commandStr = inputCommand.trim().replaceAll("\\s.*", "");

        String argsStr = inputCommand.trim().replaceAll("^\\S+\\s+(.+)$", "$1");
        if (commandStr.equals(argsStr)) argsStr = "default";

        inputStrList.add(commandStr);
        inputStrList.add(argsStr);

        log.debug("commandStr = {}, argsStr = {}", commandStr, argsStr);

        return inputStrList;
    }
}
