package com.eagrigorieva.operation;

import com.eagrigorieva.checkTool.CheckManager;
import com.eagrigorieva.enumeration.Command;
import com.eagrigorieva.model.Task;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.util.List;

@Log4j2
@AllArgsConstructor
public class OperationFactory {

    private CheckManager checkManager;

    public Operation createOperation(Command command, List<Task> taskList, String argsStr) {
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
