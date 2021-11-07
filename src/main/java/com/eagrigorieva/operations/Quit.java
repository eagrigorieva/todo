package com.eagrigorieva.operations;
import lombok.Data;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;

import java.io.BufferedReader;

@Data
@Log4j2
public class Quit extends OperationManager{

    @SneakyThrows
    public void quit(BufferedReader scanner) {
        log.debug("Exit");
        System.out.println("Exit");
        scanner.close();
        System.exit(0);
    }
}
