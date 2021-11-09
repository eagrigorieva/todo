package com.eagrigorieva.operation;
import com.eagrigorieva.model.EditArgs;
import com.eagrigorieva.model.Task;
import com.eagrigorieva.model.TaskStorage;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.util.List;

@AllArgsConstructor
@Log4j2
public class Edit extends Operation {

    private TaskStorage taskList;
    private EditArgs editArgs;

    @Override
    public void execute() {
        int id = editArgs.getId();
        String description = editArgs.getDescription();

        if (validateId(taskList, id)) {
            if (description != null) {
                taskList.get(id).setDescription(description);
                log.debug("Task is edited");
                System.out.println(SUCCESS);
            } else {
                log.error(INCORRECT_DESCRIPTION);
                System.out.println(TASK_NOT_FOUND);
            }
        } else {
            log.error(TASK_NOT_FOUND);
            System.out.println(TASK_NOT_FOUND);
        }
    }
}
