package com.eagrigorieva.operations;

import com.eagrigorieva.todoEntities.Task;
import lombok.Data;
import lombok.extern.log4j.Log4j2;

import java.util.List;

import static com.eagrigorieva.enums.TaskStatus.CREATED;

@Data
@Log4j2
public abstract class OperationManager {
    
    public void printTaskList(List<Task> taskList, Task task) {
        log.debug("{}. [{}] {}", taskList.indexOf(task), task.getTaskStatus() == CREATED ? "" : "x", task.getDescription());
        System.out.printf("%d. [%s] %s\n", taskList.indexOf(task), task.getTaskStatus() == CREATED ? "" : "x", task.getDescription());
    }
}
