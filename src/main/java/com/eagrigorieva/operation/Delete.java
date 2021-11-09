package com.eagrigorieva.operation;
import com.eagrigorieva.model.Task;
import com.eagrigorieva.model.TaskStorage;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.util.List;

@AllArgsConstructor
@Log4j2
public class Delete extends Operation {

    private TaskStorage taskList;
    private int id;

    @Override
    public void execute() {
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
