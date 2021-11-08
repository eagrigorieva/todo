package com.eagrigorieva.operation;
import com.eagrigorieva.model.Task;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.log4j.Log4j2;

import java.util.List;
import java.util.UUID;

import static com.eagrigorieva.enumeration.TaskStatus.CREATED;
import static com.eagrigorieva.checkTool.CheckManager.*;
@AllArgsConstructor
@Log4j2
public class Add extends Operation {

    private List<Task> taskList;
    private String description;

    @Override
    public void execute() {
        taskList.add(new Task(UUID.randomUUID().toString(), description, CREATED));
        log.debug("Task {} description created", description);
        System.out.println(SUCCESS);
    }
}