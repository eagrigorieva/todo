package com.eagrigorieva;

import com.eagrigorieva.todoEntities.Menu;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {
        new Menu(new BufferedReader(new InputStreamReader(System.in))).run();
    }
}
