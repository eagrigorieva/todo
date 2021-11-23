package com.eagrigorieva.console;

import com.eagrigorieva.enumeration.Command;
import com.eagrigorieva.operation.factory.OperationFactoryCreator;
import com.eagrigorieva.parser.OperationParser;
import com.eagrigorieva.parser.UserInput;
import com.eagrigorieva.storage.TaskStorage;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;

import static com.eagrigorieva.enumeration.Command.INCORRECT;
import static com.eagrigorieva.enumeration.Command.QUIT;

@Slf4j
@Component
public class Menu {

    public static final String INCORRECT_COMMAND = "Incorrect command";
    @Autowired
    private OperationParser operationParser;
    @Autowired
    private OperationFactoryCreator operationCreator;
    @Autowired
    private TaskStorage taskList;

    public void startMenu() {
        System.out.println("-----------------------------");
        System.out.println("Choose command:\n add \n print \n toggle \n edit \n search \n delete \n quit ");
        System.out.println("-----------------------------\n");
    }

    @SneakyThrows
    public void run(BufferedReader reader) {

        if (reader != null) {
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
