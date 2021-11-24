package com.eagrigorieva.operation.factory;

import com.eagrigorieva.enumeration.Command;
import com.eagrigorieva.operation.Delete;
import com.eagrigorieva.operation.Operation;
import org.springframework.stereotype.Component;

@Component
public class DeleteFactory implements OperationFactory {

    @Override
    public Operation createOperation() {
        return new Delete();
    }

    @Override
    public boolean checkFactoryMethod(Command command) {
        return command == Command.DELETE;
    }
}
