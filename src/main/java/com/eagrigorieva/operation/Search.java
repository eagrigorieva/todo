package com.eagrigorieva.operation;

import com.eagrigorieva.model.Task;
import com.eagrigorieva.model.TaskStorage;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Log4j2
public class Search extends Operation {

    private TaskStorage taskList;
    private String subStr;

    @Override
    public void execute() {
        List<Task> foundTasks = taskList.getTaskList().stream()
                .filter(task -> task.getDescription().contains(subStr))
                .collect(Collectors.toList());
        if (foundTasks.isEmpty()) {
            log.error(TASK_NOT_FOUND);
            System.out.println(TASK_NOT_FOUND);
        }
        foundTasks.forEach(task -> print(taskList.indexOf(task), task));
    }
}
