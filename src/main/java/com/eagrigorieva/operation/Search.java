package com.eagrigorieva.operation;

import com.eagrigorieva.exception.EmptyArgsListException;
import com.eagrigorieva.model.Task;
import com.eagrigorieva.storage.TaskStorage;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@AllArgsConstructor
public class Search extends Operation {

    @Override
    public List<Task> execute(TaskStorage taskList, List<String> args) {
        if (args.isEmpty()) {
            log.error(TASK_NOT_FOUND);
            System.out.println(TASK_NOT_FOUND);
            throw new EmptyArgsListException();
        }
        return taskList.getTaskList().stream()
                .filter(task -> task.getDescription().contains(args.get(0)))
                .collect(Collectors.toList());
    }
}
