package com.eagrigorieva.operation.factory;

import com.eagrigorieva.enumeration.Command;
import com.eagrigorieva.operation.Add;
import com.eagrigorieva.operation.Operation;
import org.springframework.stereotype.Component;

@Component
public class AddFactory implements OperationFactory {

    @Override
    public Operation createOperation() {
        return new Add();
    }

    @Override
    public boolean checkFactoryMethod(Command command) {
        return command == Command.ADD;
    }
}
