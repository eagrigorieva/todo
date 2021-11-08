package com.eagrigorieva.operation;

import com.eagrigorieva.model.Task;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.util.List;

import static com.eagrigorieva.enumeration.TaskStatus.COMPLETED;
import static com.eagrigorieva.enumeration.TaskStatus.CREATED;

@Log4j2
@AllArgsConstructor
public class Toggle extends Operation {

    private List<Task> taskList;
    private int id;

    @Override
    public void execute() {
        if (validateId(taskList, id)) {
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
