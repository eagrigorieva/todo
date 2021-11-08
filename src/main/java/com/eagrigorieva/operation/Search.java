package com.eagrigorieva.operation;

import com.eagrigorieva.enumeration.PrintMod;
import com.eagrigorieva.model.Task;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.log4j.Log4j2;

import java.util.List;
import java.util.stream.Collectors;

import static com.eagrigorieva.checkTool.CheckManager.*;

@AllArgsConstructor
@Log4j2
public class Search extends Operation {

    private List<Task> taskList;
    private String subStr;

    @Override
    public void execute() {
        List<Task> foundTasks = taskList.stream()
                .filter(task -> task.getDescription().contains(subStr))
                .collect(Collectors.toList());
        if (foundTasks.isEmpty()) {
            log.error(TASK_NOT_FOUND);
            System.out.println(TASK_NOT_FOUND);
        }
        foundTasks.forEach(task -> printTaskList(taskList, task.getId()));
    }
}
