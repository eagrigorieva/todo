package com.eagrigorieva.operations;
import com.eagrigorieva.todoEntities.Task;
import lombok.Data;
import lombok.extern.log4j.Log4j2;

import java.util.List;

import static com.eagrigorieva.checkTools.CheckManager.*;

@Data
@Log4j2
public class Delete extends OperationManager{

    public void delete(int id, List<Task> taskList) {
        if (id != -1) {
            taskList.remove(taskList.get(id));
            log.debug("Task is deleted");
            System.out.println(SUCCESS);
        } else {
            log.error(TASK_NOT_FOUND);
            System.out.println(TASK_NOT_FOUND);
        }
    }
}
