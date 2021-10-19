package com.eagrigorieva.todolist;

import com.eagrigorieva.enums.PrintMod;
import com.eagrigorieva.enums.TaskStatus;
import lombok.Data;
import lombok.SneakyThrows;

import java.io.BufferedReader;

import static com.eagrigorieva.enums.TaskStatus.COMPLETED;
import static com.eagrigorieva.enums.TaskStatus.CREATED;

@Data
public class Task {

    public static final String TASK_NOT_CREATED = "No tasks created";
    private static final String PRINT_ALL_TASKS_COMPLETED = "All tasks is completed";
    private static final String PRINT_NO_ONE_TASKS_COMPLETED = "No one task completed";
    public static final String INCORRECT_COMMAND = "Incorrect command";
    public static final String INCORRECT_AGS = "The argument is not recognized, the \"output all tasks\" mod is activated";

    String id;
    String description;
    TaskStatus taskStatus;

    public Task(String description, TaskStatus taskStatus) {
        this.description = description;
        this.taskStatus = taskStatus;
    }

    public static Task createTask(String description) {

        Task task = new Task(description, CREATED);
        System.out.printf("Task \"%s\" created\n", description);
        return task;
    }

    public static void printTask(PrintMod modCommand, Task task) {
        int count = 0;

        switch (modCommand) {
            case ALL:
                System.out.printf("%d. [%s] %s\n", count, task.taskStatus == CREATED ? "" : "x", task.description);
                break;
            case CREATED:
                if (task.taskStatus == COMPLETED) {
                    System.out.println(PRINT_ALL_TASKS_COMPLETED);
                } else if (task.taskStatus == CREATED) {
                    System.out.printf("%d. [] %s\n", count, task.description);
                }
                break;
            case COMPLETED:
                if (task.taskStatus == CREATED) {
                    System.out.println(PRINT_NO_ONE_TASKS_COMPLETED);
                } else if (task.taskStatus == COMPLETED) {
                    System.out.printf("%d. [x] %s\n", count, task.description);
                }

        }
    }

    public static void toggle(Task task) {
        task.taskStatus = task.taskStatus == CREATED ? COMPLETED : CREATED;
        System.out.println("Status changed: " + task.taskStatus.name() + "\n");
    }

    @SneakyThrows
    public static void quit(BufferedReader scanner) {
        System.out.println("Exit from the program, bye-bye task :(\n");
        scanner.close();
        System.exit(0);
    }


}
