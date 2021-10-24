package com.eagrigorieva.todolist;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Task task = null;
        new Menu(new BufferedReader(new InputStreamReader(System.in))).run(task);
    }
}
