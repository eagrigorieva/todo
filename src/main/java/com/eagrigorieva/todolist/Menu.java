package com.eagrigorieva.todolist;

import com.eagrigorieva.enums.Command;
import com.eagrigorieva.enums.PrintMod;

import java.util.Scanner;

import static com.eagrigorieva.enums.Command.*;
import static com.eagrigorieva.enums.PrintMod.*;
import static com.eagrigorieva.todolist.Task.*;

public class Menu {

    private final Scanner scanner;

    public Menu(Scanner scanner) {
        this.scanner = scanner;
    }

    public void startMenu() {
        System.out.println("-----------------------------");
        System.out.println("Choose command:\n create \n print \n toggle \n quit ");
        System.out.println("-----------------------------\n");
    }


    public void run(Task task) {

        Command command = null;

        if (this.scanner != null) {
            do {
                startMenu();
                String inputCommand = scanner.nextLine();

                //первая часть строки до пробела
                String commandStr = inputCommand.replaceAll("\\s.*", "");
                //вторая часть строки до пробела
                String argsStr = inputCommand.replaceAll("^\\S+\\s+(.+)$", "$1");
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
            return CREATED_TASKS;
        }

        if (modStr.equalsIgnoreCase("created")) {
            return CREATED_TASKS;
        } else if (modStr.equalsIgnoreCase("all")) {
            return ALL_TASKS;
        } else if (modStr.equalsIgnoreCase("completed")) {
            return COMPLETED_TASKS;
        } else {
            System.out.println(INCORRECT_AGS);
            return ALL_TASKS;
        }

    }

    public Command validateCommand(Task task, String commandStr) {

        if (commandStr.equalsIgnoreCase("print")) {
            validateTask(task);
            return PRINT;
        } else if (commandStr.equalsIgnoreCase("create")) {
            return CREATE;
        } else if (commandStr.equalsIgnoreCase("toggle")) {
            validateTask(task);
            return TOGGLE;
        } else if (commandStr.equalsIgnoreCase("quit")) {
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
