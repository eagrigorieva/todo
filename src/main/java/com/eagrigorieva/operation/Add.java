package com.eagrigorieva.operation;

import com.eagrigorieva.model.Task;
import com.eagrigorieva.storage.TaskStorage;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.UUID;

import static com.eagrigorieva.enumeration.TaskStatus.CREATED;

@Slf4j
@AllArgsConstructor
public class Add extends Operation {

    @Override
    public void execute(TaskStorage taskList, List<String> args) {
        String description = args.isEmpty() ? "default" : String.join(" ", args);
        taskList.add(new Task(UUID.randomUUID().toString(), description, CREATED));
        log.debug("Task {} description created", description);
        System.out.println(SUCCESS);
    }
}
