package com.eagrigorieva.operation.factory;

import com.eagrigorieva.operation.Operation;
import com.eagrigorieva.operation.Toggle;

public class ToggleFactory extends OperationFactory {

    @Override
    public Operation createOperation() {
        return new Toggle();
    }
}
