package com.eagrigorieva.operations;

import com.eagrigorieva.todoEntities.Task;
import lombok.Data;
import lombok.extern.log4j.Log4j2;

import java.util.List;
import java.util.stream.Collectors;

import static com.eagrigorieva.checkTools.CheckManager.*;

@Data
@Log4j2
public class Search extends OperationManager{

    public void search(String subStr, List<Task> taskList) {
        List<Task> foundTasks = taskList.stream()
                .filter(task -> task.getDescription().contains(subStr))
                .collect(Collectors.toList());
        if (foundTasks.isEmpty()) {
            log.error(TASK_NOT_FOUND);
            System.out.println(TASK_NOT_FOUND);
        }
        foundTasks.forEach(task -> printTaskList(taskList, task));
    }
}
