package com.eagrigorieva.checkTools;

import com.eagrigorieva.enums.Command;
import com.eagrigorieva.enums.PrintMod;
import com.eagrigorieva.todoEntities.Task;
import lombok.Data;
import lombok.extern.log4j.Log4j2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.eagrigorieva.enums.Command.*;
import static com.eagrigorieva.enums.Command.INCORRECT;
import static com.eagrigorieva.enums.PrintMod.*;

@Log4j2
@Data
public class CheckManager {
    public static final String TASK_NOT_CREATED = "No tasks created";
    public static final String TASK_NOT_FOUND = "Task not found";
    public static final String INCORRECT_DESCRIPTION = "Incorrect description";
    private static final String PRINT_ALL_TASKS_COMPLETED = "All tasks is completed";
    private static final String PRINT_NO_ONE_TASKS_COMPLETED = "No one task completed";
    public static final String INCORRECT_COMMAND = "Incorrect command";
    public static final String INCORRECT_AGS = "The argument is not recognized, the \"output all tasks\" mod is activated";
    public static final String SUCCESS = "SUCCESS";

    public PrintMod validatePrintMod(String modStr) {
        if (modStr.equals("default")) {
            return CREATED;
        }

        if (modStr.equalsIgnoreCase(CREATED.toLowerCase())) {
            return CREATED;
        } else if (modStr.equalsIgnoreCase(ALL.toLowerCase())) {
            return ALL;
        } else if (modStr.equalsIgnoreCase(COMPLETED.toLowerCase())) {
            return COMPLETED;
        } else {
            log.debug(INCORRECT_AGS);
            System.out.println(INCORRECT_AGS);
            return ALL;
        }
    }

    public int parseAndValidateId(String argsStr, List<Task> taskList) {
        if (argsStr.matches("\\d+")) {
            int id = Integer.parseInt(argsStr);
            if ((id >= 0) && (id < taskList.size())) {
                return id;
            }
        }
        //специальное значение, не граница
        return -1;
    }

    public EditArgs parseEditCommand(String argsStr, List<Task> taskList) {
        List<String> argsList = Arrays.stream(argsStr.split(" "))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .collect(Collectors.toList());
        if (argsList.size() == 1) {
            return new EditArgs(parseAndValidateId(argsList.get(0), taskList), null);
        }
        if (argsList.size() != 2) {
            return new EditArgs(-1, null);
        }
        return new EditArgs(parseAndValidateId(argsList.get(0), taskList), argsList.get(1));
    }

    public Command validateCommand(List<Task> taskList, String commandStr) {

        if (commandStr.equals(PRINT.toLowerCase())) {
            return validateTask(PRINT, taskList);
        } else if (commandStr.equals(ADD.toLowerCase())) {
            return ADD;
        } else if (commandStr.equals(TOGGLE.toLowerCase())) {
            return validateTask(TOGGLE, taskList);
        } else if (commandStr.equals(DELETE.toLowerCase())) {
            return validateTask(DELETE, taskList);
        } else if (commandStr.equals(EDIT.toLowerCase())) {
            return validateTask(EDIT, taskList);
        } else if (commandStr.equals(SEARCH.toLowerCase())) {
            return validateTask(SEARCH, taskList);
        } else if (commandStr.equals(QUIT.toLowerCase())) {
            return QUIT;
        } else {
            return INCORRECT;
        }
    }

    public Command validateTask(Command command, List<Task> taskList) {
        if (taskList == null) {
            log.error(TASK_NOT_CREATED);
            System.out.println(TASK_NOT_CREATED);
            return INCORRECT;
        } else return command;
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
