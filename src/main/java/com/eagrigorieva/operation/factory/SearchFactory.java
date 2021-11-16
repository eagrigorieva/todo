package com.eagrigorieva.operation.factory;

import com.eagrigorieva.enumeration.Command;
import com.eagrigorieva.operation.Operation;
import com.eagrigorieva.operation.Print;
import com.eagrigorieva.operation.Search;

public class SearchFactory extends OperationFactory{

    @Override
    public Operation createOperation() {
        return new Search();
    }
}
