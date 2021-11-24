package com.eagrigorieva.operation.factory;

import com.eagrigorieva.enumeration.Command;
import com.eagrigorieva.operation.Operation;
import com.eagrigorieva.operation.Search;
import org.springframework.stereotype.Component;

@Component
public class SearchFactory implements OperationFactory {

    @Override
    public Operation createOperation() {
        return new Search();
    }

    @Override
    public boolean checkFactoryMethod(Command command) {
        return command == Command.SEARCH;
    }
}
