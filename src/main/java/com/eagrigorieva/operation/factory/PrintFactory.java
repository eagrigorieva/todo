package com.eagrigorieva.operation.factory;

import com.eagrigorieva.enumeration.Command;
import com.eagrigorieva.operation.Edit;
import com.eagrigorieva.operation.Operation;
import com.eagrigorieva.operation.Print;

public class PrintFactory extends OperationFactory{

    @Override
    public Operation createOperation() {
        return new Print();
    }
}
