package com.eagrigorieva.operation;
import com.eagrigorieva.enumeration.PrintMod;
import com.eagrigorieva.model.Task;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.util.List;

import static com.eagrigorieva.enumeration.TaskStatus.*;

@AllArgsConstructor
@Log4j2
public class Print extends Operation {

    private List<Task> taskList;
    private PrintMod modCommand;

    @Override
    public void execute() {
        switch (modCommand) {
            case ALL:
                taskList.forEach(task -> printTaskList(taskList, task.getId()));
                break;
            case CREATED:
                taskList.stream().filter(task -> task.getTaskStatus() == CREATED).forEach(task -> printTaskList(taskList, task.getId()));
                break;
            case COMPLETED:
                taskList.stream().filter(task -> task.getTaskStatus() == COMPLETED).forEach(task -> printTaskList(taskList, task.getId()));
        }
    }
}
