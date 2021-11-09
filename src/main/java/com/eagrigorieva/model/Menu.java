package com.eagrigorieva.model;

import com.eagrigorieva.enumeration.Command;
import com.eagrigorieva.operation.OperationFactory;
import com.eagrigorieva.tool.Parser;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;

import static com.eagrigorieva.enumeration.Command.INCORRECT;
import static com.eagrigorieva.enumeration.Command.QUIT;

@Log4j2
public class Menu {

    public static final String INCORRECT_COMMAND = "Incorrect command";
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

        TaskStorage taskList = new TaskStorageImpl();
        Parser checkManager = new Parser();
        OperationFactory operationFactory = new OperationFactory(checkManager);

        if (this.reader != null) {
            while (true) {
                startMenu();

                List<String> commandStrList = checkManager.parseInputLine(reader.readLine());
                String commandStr = commandStrList.get(0);
                String argsStr = commandStrList.get(1);

                Command command = Command.getCommand(commandStr);

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
