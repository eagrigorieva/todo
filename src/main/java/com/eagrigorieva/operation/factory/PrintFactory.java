package com.eagrigorieva.operation.factory;

import com.eagrigorieva.enumeration.Command;
import com.eagrigorieva.operation.Operation;
import com.eagrigorieva.operation.Print;
import org.springframework.stereotype.Component;

@Component
public class PrintFactory implements OperationFactory {

    @Override
    public Operation createOperation() {
        return new Print();
    }

    @Override
    public boolean checkFactoryMethod(Command command) {
        return command == Command.PRINT;
    }
}
