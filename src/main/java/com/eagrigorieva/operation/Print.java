package com.eagrigorieva.operation;

import com.eagrigorieva.enumeration.PrintMod;
import com.eagrigorieva.enumeration.TaskStatus;
import com.eagrigorieva.model.Task;
import com.eagrigorieva.storage.TaskStorage;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import static com.eagrigorieva.enumeration.TaskStatus.COMPLETED;
import static com.eagrigorieva.enumeration.TaskStatus.CREATED;

@Slf4j
@AllArgsConstructor
public class Print extends Operation {

    @Override
    public void execute(TaskStorage taskList, List<String> args) {
        PrintMod modCommand = args.isEmpty() ? PrintMod.CREATED : PrintMod.getPrintMod(args.get(0));
        switch (modCommand) {
            case ALL:
                for (int i = 0; i < taskList.size(); i++) {
                    Task task = taskList.get(i);
                    print(i, task);
                }
                break;
            case CREATED:
                printTaskWithStatus(taskList, CREATED);
                break;
            case COMPLETED:
                printTaskWithStatus(taskList, COMPLETED);
                break;
        }
    }

    private void printTaskWithStatus(TaskStorage taskList, TaskStatus status) {
        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            if (task.getTaskStatus() == status) {
                print(i, task);
            }
        }
    }
}
