package com.eagrigorieva.operation;

import com.eagrigorieva.model.Task;
import com.eagrigorieva.model.TaskStorage;
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

    protected void print(int i, Task task) {
        log.debug("{}. {}", i, task);
        System.out.printf("%d. %s\n", i, task);
    }

    protected boolean validateId(TaskStorage taskList, int id){
        return (id >= 0) && (id < taskList.size());
    }

    public abstract void execute();
}
