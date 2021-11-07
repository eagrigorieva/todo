package com.eagrigorieva;

import com.eagrigorieva.model.Menu;
import lombok.extern.log4j.Log4j2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
@Log4j2
public class Main {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            new Menu(reader).run();
        } catch (IOException e) {
            log.error(e);
        }
    }
}
