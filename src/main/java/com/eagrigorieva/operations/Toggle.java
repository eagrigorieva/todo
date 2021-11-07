package com.eagrigorieva.operations;
import com.eagrigorieva.todoEntities.Task;
import lombok.Data;
import lombok.extern.log4j.Log4j2;

import java.util.List;

import static com.eagrigorieva.enums.TaskStatus.*;
import static com.eagrigorieva.checkTools.CheckManager.*;

@Data
@Log4j2
public class Toggle extends OperationManager{

    public void toggle(int id, List<Task> taskList) {
        if (id != -1) {
            Task selectedTask = taskList.get(id);
            selectedTask.setTaskStatus(selectedTask.getTaskStatus() == CREATED ? COMPLETED : CREATED);
            log.debug("Status changed: {}", selectedTask.getTaskStatus().name());
            System.out.println(SUCCESS);
        } else {
            log.error(TASK_NOT_FOUND);
            System.out.println(TASK_NOT_FOUND);
        }
    }
}
