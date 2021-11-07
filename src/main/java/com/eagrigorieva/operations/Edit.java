package com.eagrigorieva.operations;
import com.eagrigorieva.checkTools.EditArgs;
import com.eagrigorieva.todoEntities.Task;
import lombok.Data;
import lombok.extern.log4j.Log4j2;

import java.util.List;

import static com.eagrigorieva.checkTools.CheckManager.*;

@Data
@Log4j2
public class Edit extends OperationManager{

    public void edit(EditArgs editArgs, List<Task> taskList) {
        int id = editArgs.getId();
        String description = editArgs.getDescription();

        if (id != -1) {
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
