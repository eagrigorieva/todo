package com.eagrigorieva.operation;

import com.eagrigorieva.enumeration.PrintMod;
import com.eagrigorieva.enumeration.TaskStatus;
import com.eagrigorieva.model.Task;
import com.eagrigorieva.model.TaskStorage;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.util.List;

import static com.eagrigorieva.enumeration.TaskStatus.COMPLETED;
import static com.eagrigorieva.enumeration.TaskStatus.CREATED;

@AllArgsConstructor
@Log4j2
public class Print extends Operation {

    private TaskStorage taskList;
    private PrintMod modCommand;

    @Override
    public void execute() {
        switch (modCommand) {
            case ALL:
                for (int i = 0; i < taskList.size(); i++) {
                    Task task = taskList.get(i);
                    print(i, task);
                }
                break;
            case CREATED:
                printTaskWithStatus(CREATED);
                break;
            case COMPLETED:
                printTaskWithStatus(COMPLETED);
                break;
        }
    }

    private void printTaskWithStatus(TaskStatus status) {
        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            if (task.getTaskStatus() == status) {
                print(i, task);
            }
        }
    }
}
