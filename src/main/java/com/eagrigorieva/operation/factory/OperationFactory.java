package com.eagrigorieva.operation.factory;

import com.eagrigorieva.enumeration.Command;
import com.eagrigorieva.operation.*;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@AllArgsConstructor
public abstract class OperationFactory {

    public abstract Operation createOperation(); /*{
        switch (command) {
            case ADD:
                return new Add();

            case PRINT:
                return new Print();

            case TOGGLE:
                return new Toggle();

            case DELETE:
                return new Delete();

            case EDIT:
                return new Edit();

            case SEARCH:
                return new Search();
        }
        log.error("IllegalStateException");
        throw new IllegalStateException();
    }*/
}
