package com.eagrigorieva.operation.factory;

import com.eagrigorieva.enumeration.Command;
import com.eagrigorieva.operation.Add;
import com.eagrigorieva.operation.Delete;
import com.eagrigorieva.operation.Operation;

public class DeleteFactory extends OperationFactory{

    @Override
    public Operation createOperation() {
        return new Delete();
    }
}
