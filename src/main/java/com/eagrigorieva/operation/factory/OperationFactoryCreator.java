package com.eagrigorieva.operation.factory;

import com.eagrigorieva.enumeration.Command;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class OperationFactoryCreator {
    @Autowired
    private List<OperationFactory> operationFactoryList;

    public OperationFactory createOperationFactory(Command command) {
        return operationFactoryList.stream()
                .filter(l -> l.checkFactoryMethod(command))
                .findFirst()
                .orElseThrow(() -> {
                    log.error("IllegalStateException");
                    return new IllegalStateException();
                });
    }
}
