package com.eagrigorieva.operation.factory;

import com.eagrigorieva.enumeration.Command;
import com.eagrigorieva.operation.Edit;
import com.eagrigorieva.operation.Operation;
import org.springframework.stereotype.Component;

@Component
public class EditFactory implements OperationFactory {

    @Override
    public Operation createOperation() {
        return new Edit();
    }

    @Override
    public boolean checkFactoryMethod(Command command) {
        return command == Command.EDIT;
    }
}
