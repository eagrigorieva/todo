package com.eagrigorieva.operation.factory;

import com.eagrigorieva.enumeration.Command;
import com.eagrigorieva.operation.Operation;

public interface OperationFactory {

    Operation createOperation();

    boolean checkFactoryMethod(Command command);

}
