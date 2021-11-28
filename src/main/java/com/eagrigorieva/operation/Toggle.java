package com.eagrigorieva.operation;

import com.eagrigorieva.exception.EmptyArgsListException;
import com.eagrigorieva.exception.TaskNotFoundException;
import com.eagrigorieva.model.Task;
import com.eagrigorieva.storage.TaskStorage;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.List;

import static com.eagrigorieva.enumeration.TaskStatus.COMPLETED;
import static com.eagrigorieva.enumeration.TaskStatus.CREATED;

@Slf4j
@AllArgsConstructor
public class Toggle extends Operation {

    @Override
    public List<Task> execute(TaskStorage taskList, List<String> args) {
        if (args.isEmpty()) {
            log.error(TASK_NOT_FOUND);
            System.out.println(TASK_NOT_FOUND);
            throw new EmptyArgsListException();
        }

        String id = args.get(0);
        Task task = taskList.get(id);

        if (task != null) {
            task.setTaskStatus(task.getTaskStatus() == CREATED ? COMPLETED : CREATED);
            log.debug("Status changed: {}", task.getTaskStatus().name());
            System.out.println(SUCCESS);
            return Collections.singletonList(task);

        } else {
            log.error(TASK_NOT_FOUND);
            System.out.println(TASK_NOT_FOUND);
            throw new TaskNotFoundException();
        }
    }
}
