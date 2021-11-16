package com.eagrigorieva.operation.factory;

import com.eagrigorieva.enumeration.Command;
import com.eagrigorieva.operation.Add;
import com.eagrigorieva.operation.Operation;

public class AddFactory extends OperationFactory{

    @Override
    public Operation createOperation() {
        return new Add();
    }
}
