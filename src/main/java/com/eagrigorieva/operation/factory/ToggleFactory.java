package com.eagrigorieva.operation.factory;

import com.eagrigorieva.enumeration.Command;
import com.eagrigorieva.operation.Operation;
import com.eagrigorieva.operation.Toggle;
import org.springframework.stereotype.Component;

@Component
public class ToggleFactory implements OperationFactory {

    @Override
    public Operation createOperation() {
        return new Toggle();
    }

    @Override
    public boolean checkFactoryMethod(Command command) {
        return command == Command.TOGGLE;
    }
}
