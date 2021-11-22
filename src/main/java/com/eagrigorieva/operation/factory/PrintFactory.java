package com.eagrigorieva.operation.factory;

import com.eagrigorieva.operation.Operation;
import com.eagrigorieva.operation.Print;

public class PrintFactory extends OperationFactory {

    @Override
    public Operation createOperation() {
        return new Print();
    }
}
