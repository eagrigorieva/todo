package com.eagrigorieva;

import com.eagrigorieva.console.Menu;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Slf4j
@SpringBootApplication
public class Main implements CommandLineRunner {
    @Autowired
    private Menu menu;

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Override
    public void run(String... args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            menu.run(reader);
        } catch (IOException e) {
            log.error("error", e);
        }
    }
}
