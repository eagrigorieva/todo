package com.eagrigorieva.todolist;

import com.eagrigorieva.enums.PrintMod;
import com.eagrigorieva.enums.TaskStatus;
import lombok.Data;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.PrintStream;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.eagrigorieva.enums.TaskStatus.COMPLETED;
import static com.eagrigorieva.enums.TaskStatus.CREATED;

@Log4j2
@Data
public class Task {

    public static final String TASK_NOT_CREATED = "No tasks created";
    public static final String TASK_NOT_FOUND = "Task not found";
    public static final String INCORRECT_DESCRIPTION = "Incorrect description";
    private static final String PRINT_ALL_TASKS_COMPLETED = "All tasks is completed";
    private static final String PRINT_NO_ONE_TASKS_COMPLETED = "No one task completed";
    public static final String INCORRECT_COMMAND = "Incorrect command";
    public static final String INCORRECT_AGS = "The argument is not recognized, the \"output all tasks\" mod is activated";
    public static final String SUCCESS = "SUCCESS";


    private String id;
    private String description;
    private TaskStatus taskStatus;

    public Task(String id, String description, TaskStatus taskStatus) {
        this.id = id;
        this.description = description;
        this.taskStatus = taskStatus;
    }

    public static List<Task> createTask(List<Task> taskList, String description) {
        taskList.add(new Task(UUID.randomUUID().toString(), description, CREATED));
        log.debug("Task {} description created", description);
        System.out.println(SUCCESS);
        return taskList;
    }

    public static void printTask(PrintMod modCommand, List<Task> taskList) {
        switch (modCommand) {
            case ALL:
                taskList.forEach(task -> printTask(taskList, task));
                break;
            case CREATED:
                taskList.stream().filter(task -> task.taskStatus == CREATED).forEach(task -> printTask(taskList, task));
                break;
            case COMPLETED:
                taskList.stream().filter(task -> task.taskStatus == COMPLETED).forEach(task -> printTask(taskList, task));
        }
    }

    private static void printTask(List<Task> taskList, Task task) {
        log.debug("{}. [{}] {}", taskList.indexOf(task), task.taskStatus == CREATED ? "" : "x", task.description);
        System.out.printf("%d. [%s] %s\n", taskList.indexOf(task), task.taskStatus == CREATED ? "" : "x", task.description);

    }

    public static void toggle(int id, List<Task> taskList) {
        if (id != -1) {
            Task selectedTask = taskList.get(id);
            selectedTask.taskStatus = selectedTask.taskStatus == CREATED ? COMPLETED : CREATED;
            log.debug("Status changed: {}", selectedTask.taskStatus.name());
            System.out.println(SUCCESS);
        } else {
            log.error(TASK_NOT_FOUND);
            System.out.println(TASK_NOT_FOUND);
        }
    }

    public static void delete(int id, List<Task> taskList) {
        if (id != -1) {
            taskList.remove(taskList.get(id));
            log.debug("Task is deleted");
            System.out.println(SUCCESS);
        } else {
            log.error(TASK_NOT_FOUND);
            System.out.println(TASK_NOT_FOUND);
        }

    }

    public static void search(String subStr, List<Task> taskList) {
        List<Task> foundTasks = taskList.stream()
                .filter(task -> task.getDescription().contains(subStr))
                .collect(Collectors.toList());
        if (foundTasks.isEmpty()) {
            log.error(TASK_NOT_FOUND);
            System.out.println(TASK_NOT_FOUND);
        }
        foundTasks.forEach(task -> printTask(taskList, task));
    }

    public static void edit(EditArgs editArgs, List<Task> taskList) {
        int id = editArgs.getId();
        String description = editArgs.getDescription();

        if (id != -1) {
            if (description != null) {
                taskList.get(id).description = description;
                log.debug("Task is edited");
                System.out.println(SUCCESS);
            } else {
                log.error(INCORRECT_DESCRIPTION);
                System.out.println(TASK_NOT_FOUND);
            }
        } else {
            log.error(TASK_NOT_FOUND);
            System.out.println(TASK_NOT_FOUND);
        }
    }

    @SneakyThrows
    public static void quit(BufferedReader scanner) {
        log.debug("Exit");
        System.out.println("Exit");
        scanner.close();
        System.exit(0);
    }
}
