package com.eagrigorieva.todolist;

import com.eagrigorieva.enums.Command;
import com.eagrigorieva.enums.PrintMod;
import lombok.SneakyThrows;

import java.io.BufferedReader;

import static com.eagrigorieva.enums.Command.*;
import static com.eagrigorieva.enums.PrintMod.*;
import static com.eagrigorieva.todolist.Task.*;

public class Menu {

    private final BufferedReader scanner;

    public Menu(BufferedReader scanner) {
        this.scanner = scanner;
    }

    public void startMenu() {
        System.out.println("-----------------------------");
        System.out.println("Choose command:\n create \n print \n toggle \n quit ");
        System.out.println("-----------------------------\n");
    }


    @SneakyThrows
    public void run(Task task) {

        Command command;

        if (this.scanner != null) {
            do {
                startMenu();
                String inputCommand = scanner.readLine();

                //первая часть строки до пробела
                String commandStr = inputCommand.trim().replaceAll("\\s.*", "");
                //вторая часть строки до пробела
                String argsStr = inputCommand.trim().replaceAll("^\\S+\\s+(.+)$", "$1");
                if (commandStr.equals(argsStr)) argsStr = "default";

                command = validateCommand(task, commandStr);

                switch (command) {
                    case CREATE:
                        task = createTask(argsStr);
                        break;

                    case PRINT:
                        printTask(validatePrintMod(argsStr), task);
                        break;

                    case TOGGLE:
                        toggle(task);
                        break;

                    case QUIT:
                        quit(this.scanner);
                        break;

                    case INCORRECT:
                        System.out.println(INCORRECT_COMMAND);
                        break;
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
            System.out.println(INCORRECT_AGS);
            return ALL;
        }

    }

    public Command validateCommand(Task task, String commandStr) {

        if (commandStr.equalsIgnoreCase(PRINT.toLowerCase())) {
            validateTask(task);
            return PRINT;
        } else if (commandStr.equalsIgnoreCase(CREATE.toLowerCase())) {
            return CREATE;
        } else if (commandStr.equalsIgnoreCase(TOGGLE.toLowerCase())) {
            validateTask(task);
            return TOGGLE;
        } else if (commandStr.equalsIgnoreCase(QUIT.toLowerCase())) {
            return QUIT;
        } else {
            return INCORRECT;
        }
    }

    public void validateTask(Task task){
        if (task == null) {
            System.out.println(TASK_NOT_CREATED);
            quit(this.scanner);
        }
    }
}
