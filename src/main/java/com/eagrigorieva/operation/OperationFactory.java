package com.eagrigorieva.operation;

import com.eagrigorieva.enumeration.Command;
import com.eagrigorieva.enumeration.PrintMod;
import com.eagrigorieva.model.Task;
import com.eagrigorieva.model.TaskStorage;
import com.eagrigorieva.tool.Parser;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.util.List;

@Log4j2
@AllArgsConstructor
public class OperationFactory {

    private Parser parser;

    public Operation createOperation(Command command, TaskStorage taskList, String argsStr) {
        switch (command) {
            case ADD:
                return new Add(taskList, argsStr);

            case PRINT:
                return new Print(taskList, PrintMod.getPrintMod(argsStr));

            case TOGGLE:
                return new Toggle(taskList, parser.parseStrToInt(argsStr));

            case DELETE:
                return new Delete(taskList, parser.parseStrToInt(argsStr));

            case EDIT:
                return new Edit(taskList, parser.parseEditCommand(argsStr));

            case SEARCH:
                return new Search(taskList, argsStr);
        }
        log.error("IllegalStateException");
        throw new IllegalStateException();
    }
}
