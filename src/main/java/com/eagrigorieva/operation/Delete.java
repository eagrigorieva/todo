package com.eagrigorieva.operation;

import com.eagrigorieva.storage.TaskStorage;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;


@Slf4j
@AllArgsConstructor
public class Delete extends Operation {

    @Override
    public void execute(TaskStorage taskList, List<String> args) {
        if (args.isEmpty()) {
            log.error(TASK_NOT_FOUND);
            System.out.println(TASK_NOT_FOUND);
            return;
        }
        int id = parseStrToInt(args.get(0));
        if (validateId(taskList, id)) {
            taskList.remove(id);
            log.debug("Task is deleted");
            System.out.println(SUCCESS);
        } else {
            log.error(TASK_NOT_FOUND);
            System.out.println(TASK_NOT_FOUND);
        }
    }
}
