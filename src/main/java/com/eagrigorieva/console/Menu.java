package com.eagrigorieva.console;

import com.eagrigorieva.enumeration.Command;
import com.eagrigorieva.operation.factory.OperationFactoryCreator;
import com.eagrigorieva.parser.OperationParser;
import com.eagrigorieva.parser.UserInput;
import com.eagrigorieva.storage.TaskStorage;
import com.eagrigorieva.storage.TaskStorageList;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;

import java.io.BufferedReader;

import static com.eagrigorieva.enumeration.Command.INCORRECT;
import static com.eagrigorieva.enumeration.Command.QUIT;

@Log4j2
public class Menu {

    public static final String INCORRECT_COMMAND = "Incorrect command";
    private final BufferedReader reader;
    private final OperationParser operationParser = new OperationParser();
    private final OperationFactoryCreator operationCreator = new OperationFactoryCreator();

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
        TaskStorage taskList = new TaskStorageList();

        if (this.reader != null) {
            while (true) {
                startMenu();

                UserInput commandStrList = operationParser.getParseOperation(reader.readLine());
                Command command = commandStrList.getCommand();

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

                operationCreator.createOperationFactory(command).createOperation().execute(taskList, commandStrList.getArgList());
            }
        }
    }
}
