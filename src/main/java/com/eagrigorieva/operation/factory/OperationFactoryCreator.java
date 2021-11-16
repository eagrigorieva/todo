package com.eagrigorieva.operation.factory;

import com.eagrigorieva.enumeration.Command;
import com.eagrigorieva.operation.Operation;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class OperationFactoryCreator {
    public OperationFactory createOperationFactory(Command command) {
        switch (command) {
            case ADD:
                return new AddFactory();

            case PRINT:
                return new PrintFactory();

            case TOGGLE:
                return new ToggleFactory();

            case DELETE:
                return new DeleteFactory();

            case EDIT:
                return new EditFactory();

            case SEARCH:
                return new SearchFactory();
        }
        log.error("IllegalStateException");
        throw new IllegalStateException();
    }
}
