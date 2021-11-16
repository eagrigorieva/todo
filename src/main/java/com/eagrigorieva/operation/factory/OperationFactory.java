package com.eagrigorieva.operation.factory;

import com.eagrigorieva.operation.Operation;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@AllArgsConstructor
public abstract class OperationFactory {

    public abstract Operation createOperation();

}
