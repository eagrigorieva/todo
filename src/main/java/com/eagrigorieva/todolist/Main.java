package com.eagrigorieva.todolist;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        new Menu(new BufferedReader(new InputStreamReader(System.in))).run();
    }
}
