package com.eagrigorieva;

import com.eagrigorieva.console.Menu;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class Main {

    @Autowired
    private Menu menu;

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}
