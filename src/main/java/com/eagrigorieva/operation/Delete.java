package com.eagrigorieva.operation;

import com.eagrigorieva.exception.TaskNotFoundException;
import com.eagrigorieva.model.Task;
import com.eagrigorieva.storage.TaskStorage;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.List;


@Slf4j
@AllArgsConstructor
public class Delete extends Operation {

    @Override
    public List<Task> execute(TaskStorage taskList, List<String> args) {
        if (args.isEmpty()) {
            log.error(TASK_NOT_FOUND);
            System.out.println(TASK_NOT_FOUND);
            return Collections.emptyList();

        }
        String id = args.get(0);
        if (taskList.remove(id)) {
            log.debug("Task is deleted");
            System.out.println(SUCCESS);
            return Collections.emptyList();
        } else {
            log.error(TASK_NOT_FOUND);
            System.out.println(TASK_NOT_FOUND);
            throw new TaskNotFoundException();
        }
    }
}
