package com.eagrigorieva.operations;
import com.eagrigorieva.enums.PrintMod;
import com.eagrigorieva.todoEntities.Task;
import lombok.Data;
import lombok.extern.log4j.Log4j2;

import java.util.List;

import static com.eagrigorieva.enums.TaskStatus.*;

@Data
@Log4j2
public class Print extends OperationManager{

    public void printTask(PrintMod modCommand, List<Task> taskList) {
        switch (modCommand) {
            case ALL:
                taskList.forEach(task -> printTaskList(taskList, task));
                break;
            case CREATED:
                taskList.stream().filter(task -> task.getTaskStatus() == CREATED).forEach(task -> printTaskList(taskList, task));
                break;
            case COMPLETED:
                taskList.stream().filter(task -> task.getTaskStatus() == COMPLETED).forEach(task -> printTaskList(taskList, task));
        }
    }
}
