package com.eagrigorieva.operation;

import com.eagrigorieva.model.Task;
import com.eagrigorieva.storage.TaskStorage;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

import static com.eagrigorieva.enumeration.TaskStatus.CREATED;

@Slf4j
@AllArgsConstructor
public class Create extends Operation {

    @Override
    public List<Task> execute(TaskStorage taskList, List<String> args) {
        List<Task> result = new ArrayList<>();
        String description = args.isEmpty() ? "default" : String.join(" ", args);
        Task task = new Task();
        task.setDescription(description);
        task.setTaskStatus(CREATED);
        taskList.save(task);
        result.add(task);
        log.debug("Task {} description created", description);
        System.out.println(SUCCESS);
        return result;
    }
}
