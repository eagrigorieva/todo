package com.eagrigorieva.operation;

import com.eagrigorieva.enumeration.PrintMod;
import com.eagrigorieva.enumeration.TaskStatus;
import com.eagrigorieva.model.Task;
import com.eagrigorieva.storage.TaskStorage;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static com.eagrigorieva.enumeration.TaskStatus.COMPLETED;
import static com.eagrigorieva.enumeration.TaskStatus.CREATED;

@Slf4j
@AllArgsConstructor
public class Print extends Operation {

    @Override
    public List<Task> execute(TaskStorage taskList, List<String> args) {
        PrintMod modCommand = args.isEmpty() ? PrintMod.CREATED : PrintMod.getPrintMod(args.get(0));
        switch (modCommand) {
            case ALL:
                return taskList.getTaskList();
            case CREATED:
                return getTaskListByStatus(taskList, CREATED);
            case COMPLETED:
                return getTaskListByStatus(taskList, COMPLETED);
        }
        return Collections.emptyList();
    }

    private List<Task> getTaskListByStatus(TaskStorage taskList, TaskStatus status) {
        return taskList.getTaskList()
                .stream()
                .filter(t -> t.getTaskStatus() == status)
                .collect(Collectors.toList());
    }
}
