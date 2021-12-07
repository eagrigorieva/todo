package com.eagrigorieva.operation;

import com.eagrigorieva.exception.EmptyArgsListException;
import com.eagrigorieva.exception.TaskNotFoundException;
import com.eagrigorieva.model.Task;
import com.eagrigorieva.storage.TaskStorage;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.List;


@Slf4j
@AllArgsConstructor
public class Edit extends Operation {

    @Override
    public List<Task> execute(TaskStorage taskList, List<String> args) {
        if (args.size() < 2) {
            log.error(TASK_NOT_FOUND);
            System.out.println(TASK_NOT_FOUND);
            throw new EmptyArgsListException();
        }

        Long id = Long.parseLong(args.get(0));
        String description = args.get(1);
        Task task = taskList.get(id);

        if (task != null) {
            task.setDescription(description);
            System.out.println(SUCCESS);
            log.debug("Task is edited");
            return Collections.singletonList(task);

        } else {
            log.error(TASK_NOT_FOUND);
            System.out.println(TASK_NOT_FOUND);
            throw new TaskNotFoundException();
        }
    }
}