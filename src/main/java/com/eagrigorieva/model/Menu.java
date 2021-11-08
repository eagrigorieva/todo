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

        List<Task> taskList = new ArrayList<>();
        CheckManager checkManager = new CheckManager();
        OperationFactory operationFactory = new OperationFactory(checkManager);

        if (this.reader != null) {
            while (true) {
                startMenu();

                List<String> commandStrList = checkManager.parseInputLine(reader.readLine());
                String commandStr = commandStrList.get(0);
                String argsStr = commandStrList.get(1);

                Command command = checkManager.validateCommand(taskList, commandStr);

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

                operationFactory.createOperation(command, taskList, argsStr).execute();
            }
        }
    }
}
