package com.eagrigorieva.operation;

import com.eagrigorieva.dto.TaskDto;
import com.eagrigorieva.model.Task;
import com.eagrigorieva.storage.TaskStorage;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Data
@Slf4j
public abstract class Operation {

    public static final String TASK_NOT_FOUND = "Task not found";
    public static final String INCORRECT_DESCRIPTION = "Incorrect description";
    public static final String SUCCESS = "SUCCESS";

    protected int parseStrToInt(String inputString) {
        if (inputString.matches("\\d+")) {
            return Integer.parseInt(inputString);
        }
        return -1;
    }

    public abstract List<Task> execute(TaskStorage taskList, List<String> args);
}
