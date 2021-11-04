package com.eagrigorieva.todolist;

import com.eagrigorieva.enums.Command;
import com.eagrigorieva.enums.PrintMod;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.eagrigorieva.enums.Command.*;
import static com.eagrigorieva.enums.PrintMod.*;
import static com.eagrigorieva.todolist.Task.*;
@Log4j2
public class Menu {

    private final BufferedReader scanner;

    public Menu(BufferedReader scanner) {
        this.scanner = scanner;
    }

    public void startMenu() {
        System.out.println("-----------------------------");
        System.out.println("Choose command:\n add \n print \n toggle \n edit \n search \n delete \n quit ");
        System.out.println("-----------------------------\n");
    }

    @SneakyThrows
    public void run() {

        Command command;
        List<Task> taskList = new ArrayList<>();

        if (this.scanner != null) {
            do {
                startMenu();
                String inputCommand = scanner.readLine();

                //первая часть строки до пробела
                String commandStr = inputCommand.trim().replaceAll("\\s.*", "");
                //вторая часть строки до пробела
                String argsStr = inputCommand.trim().replaceAll("^\\S+\\s+(.+)$", "$1");
                if (commandStr.equals(argsStr)) argsStr = "default";
                log.debug("commandStr = {}, argsStr = {}", commandStr, argsStr);
                command = validateCommand(taskList, commandStr);

                switch (command) {
                    case ADD:
                        createTask(taskList, argsStr);
                        break;

                    case PRINT:
                        printTask(validatePrintMod(argsStr), taskList);
                        break;

                    case TOGGLE:
                        toggle(parseAndValidateId(argsStr, taskList), taskList);
                        break;

                    case DELETE:
                        delete(parseAndValidateId(argsStr, taskList), taskList);
                        break;

                    case EDIT:
                        edit(parseEditCommand(argsStr, taskList), taskList);
                        break;

                    case SEARCH:
                        search(argsStr, taskList);
                        break;

                    case QUIT:
                        quit(this.scanner);
                        break;

                    case INCORRECT:{
                        log.error(INCORRECT_COMMAND);
                        System.out.println(INCORRECT_COMMAND);
                        break;}
                }
            } while (command != QUIT);
        }
    }

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
}
