package com.eagrigorieva.todolist;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Task task = null;

        new Menu(new Scanner(System.in)).run(task);

    }
}
