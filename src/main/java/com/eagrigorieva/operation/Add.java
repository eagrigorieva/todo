package com.eagrigorieva.operation;
import com.eagrigorieva.model.Task;
import com.eagrigorieva.model.TaskStorage;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.util.List;
import java.util.UUID;

import static com.eagrigorieva.enumeration.TaskStatus.CREATED;

@Log4j2
@AllArgsConstructor
public class Add extends Operation {

    private TaskStorage taskList;
    private String description;

    @Override
    public void execute() {
        taskList.add(new Task(UUID.randomUUID().toString(), description, CREATED));
        log.debug("Task {} description created", description);
        System.out.println(SUCCESS);
    }
}
