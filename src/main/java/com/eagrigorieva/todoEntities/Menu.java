package com.eagrigorieva.todoEntities;

import com.eagrigorieva.enums.Command;
import com.eagrigorieva.operations.*;
import com.eagrigorieva.checkTools.CheckManager;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;

import static com.eagrigorieva.enums.Command.*;
import static com.eagrigorieva.checkTools.CheckManager.*;
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
        Add addOperation = new Add();
        Delete deleteOperation = new Delete();
        Edit editOperation = new Edit();
        Print printOperation = new Print();
        Quit quitOperation = new Quit();
        Search searchOperation = new Search();
        Toggle toggleOperation = new Toggle();
        CheckManager checkManager = new CheckManager();

        if (this.scanner != null) {
            do {
                startMenu();

                List<String> commandStrList = checkManager.parseInputLine(scanner.readLine());

                String commandStr = commandStrList.get(0);
                String argsStr = commandStrList.get(1);

                command = checkManager.validateCommand(taskList, commandStr);

                switch (command) {
                    case ADD:
                        addOperation.add(taskList, argsStr);
                        break;

                    case PRINT:
                        printOperation.printTask(checkManager.validatePrintMod(argsStr), taskList);
                        break;

                    case TOGGLE:
                        toggleOperation.toggle(checkManager.parseAndValidateId(argsStr, taskList), taskList);
                        break;

                    case DELETE:
                        deleteOperation.delete(checkManager.parseAndValidateId(argsStr, taskList), taskList);
                        break;

                    case EDIT:
                        editOperation.edit(checkManager.parseEditCommand(argsStr, taskList), taskList);
                        break;

                    case SEARCH:
                        searchOperation.search(argsStr, taskList);
                        break;

                    case QUIT:
                        quitOperation.quit(this.scanner);
                        break;

                    case INCORRECT:{
                        log.error(INCORRECT_COMMAND);
                        System.out.println(INCORRECT_COMMAND);
                        break;}
                }
            } while (command != QUIT);
        }
    }
}
