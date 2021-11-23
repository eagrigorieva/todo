package com.eagrigorieva.operation;

import com.eagrigorieva.storage.TaskStorage;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;


@Slf4j
@AllArgsConstructor
public class Edit extends Operation {

    @Override
    public void execute(TaskStorage taskList, List<String> args) {
        if (args.isEmpty()) {
            log.error(TASK_NOT_FOUND);
            System.out.println(TASK_NOT_FOUND);
            return;
        }

        if (args.size() < 2) {
            log.error(INCORRECT_DESCRIPTION);
            System.out.println(INCORRECT_DESCRIPTION);
            return;
        }

        int id = parseStrToInt(args.get(0));
        String description = String.join(" ", args.subList(1, args.size()));

        if (validateId(taskList, id)) {
            taskList.get(id).setDescription(description);
            System.out.println(SUCCESS);
            log.debug("Task is edited");
        } else {
            log.error(TASK_NOT_FOUND);
            System.out.println(TASK_NOT_FOUND);
        }
    }
}