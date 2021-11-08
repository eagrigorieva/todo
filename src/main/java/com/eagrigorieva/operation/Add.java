package com.eagrigorieva.operation;
import com.eagrigorieva.model.Task;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.util.List;
import java.util.UUID;

import static com.eagrigorieva.enumeration.TaskStatus.CREATED;

@AllArgsConstructor
@Log4j2
public class Add extends Operation {

    private List<Task> taskList;
    private String description;

    @Override
    public void execute() {
        int newIndex = taskList.size();
        taskList.add(new Task(newIndex, description, CREATED));
        log.debug("Task {} description created", description);
        System.out.println(SUCCESS);
    }
}
