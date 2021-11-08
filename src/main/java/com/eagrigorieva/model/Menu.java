package com.eagrigorieva.model;

import com.eagrigorieva.enumeration.Command;
import com.eagrigorieva.operation.*;
import com.eagrigorieva.checkTool.CheckManager;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;

import static com.eagrigorieva.enumeration.Command.*;
import static com.eagrigorieva.checkTool.CheckManager.*;

@Log4j2
public class Menu {

    private final BufferedReader reader;

    public Menu(BufferedReader reader) {
        this.reader = reader;
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

        CheckManager checkManager = new CheckManager();

        if (this.reader != null) {
            while (true) {
                startMenu();

                List<String> commandStrList = checkManager.parseInputLine(reader.readLine());

                String commandStr = commandStrList.get(0);
                String argsStr = commandStrList.get(1);

                command = checkManager.validateCommand(taskList, commandStr);

                if (command == QUIT) {
                    log.debug("Exit");
                    System.out.println("Exit");
                    return;
                }

                if (command == INCORRECT) {
                    log.error(INCORRECT_COMMAND);
                    System.out.println(INCORRECT_COMMAND);
                    continue;
                }
                createOperation(command, taskList, checkManager, argsStr).execute();
            }
        }
    }

    private Operation createOperation(Command command, List<Task> taskList, CheckManager checkManager, String argsStr) {
        switch (command) {
            case ADD:
                return new Add(taskList, argsStr);

            case PRINT:
                return new Print(taskList, checkManager.validatePrintMod(argsStr));

            case TOGGLE:
                return new Toggle(taskList, checkManager.parseAndValidateId(argsStr, taskList));

            case DELETE:
                return new Delete(taskList, checkManager.parseAndValidateId(argsStr, taskList));

            case EDIT:
                return new Edit(taskList, checkManager.parseEditCommand(argsStr, taskList));

            case SEARCH:
                return new Search(taskList, argsStr);
        }
        log.error("IllegalStateException");
        throw new IllegalStateException();
    }
}
