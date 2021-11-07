package com.eagrigorieva.operations;
import com.eagrigorieva.todoEntities.Task;
import lombok.Data;
import lombok.extern.log4j.Log4j2;

import java.util.List;
import java.util.UUID;

import static com.eagrigorieva.enums.TaskStatus.CREATED;
import static com.eagrigorieva.checkTools.CheckManager.*;
@Data
@Log4j2
public class Add extends OperationManager{

    public List<Task> add(List<Task> taskList, String description) {
        taskList.add(new Task(UUID.randomUUID().toString(), description, CREATED));
        log.debug("Task {} description created", description);
        System.out.println(SUCCESS);
        return taskList;
    }
}
