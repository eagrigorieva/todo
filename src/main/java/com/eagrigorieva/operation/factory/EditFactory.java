package com.eagrigorieva.operation.factory;

import com.eagrigorieva.operation.Edit;
import com.eagrigorieva.operation.Operation;

public class EditFactory extends OperationFactory {

    @Override
    public Operation createOperation() {
        return new Edit();
    }
}
