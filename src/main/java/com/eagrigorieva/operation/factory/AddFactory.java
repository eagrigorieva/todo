package com.eagrigorieva.operation.factory;

import com.eagrigorieva.enumeration.Command;
import com.eagrigorieva.operation.Create;
import com.eagrigorieva.operation.Operation;
import org.springframework.stereotype.Component;

@Component
public class AddFactory implements OperationFactory {

    @Override
    public Operation createOperation() {
        return new Create();
    }

    @Override
    public boolean checkFactoryMethod(Command command) {
        return command == Command.ADD;
    }
}