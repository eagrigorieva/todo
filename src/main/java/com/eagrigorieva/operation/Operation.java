package com.eagrigorieva.operation;

import com.eagrigorieva.model.Task;
import lombok.Data;
import lombok.extern.log4j.Log4j2;

import java.util.List;

import static com.eagrigorieva.enumeration.TaskStatus.CREATED;

@Data
@Log4j2
public abstract class Operation {

    public static final String TASK_NOT_FOUND = "Task not found";
    public static final String INCORRECT_DESCRIPTION = "Incorrect description";
    public static final String SUCCESS = "SUCCESS";

    protected void printTaskList(List<Task> taskList, String id) {
        Task selectedTask = null;
        for (Task task : taskList) {
            if (task.getId().equals(id)) {
                selectedTask = task;
                break;
            }
        }
        log.debug("{}. [{}] {}", taskList.indexOf(selectedTask), selectedTask.getTaskStatus() == CREATED ? "" : "x", selectedTask.getDescription());
        System.out.printf("%d. [%s] %s\n", taskList.indexOf(selectedTask), selectedTask.getTaskStatus() == CREATED ? "" : "x", selectedTask.getDescription());
    }

    protected boolean validateId(List<Task> taskList, int id){
        return (id >= 0) && (id < taskList.size());
    }

    public abstract void execute();
}
