package com.eagrigorieva.operation;

import com.eagrigorieva.model.Task;
import lombok.Data;
import lombok.extern.log4j.Log4j2;

import java.util.List;

import static com.eagrigorieva.enumeration.TaskStatus.CREATED;

@Data
@Log4j2
public abstract class Operation {
    
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

    public abstract void execute();
}
