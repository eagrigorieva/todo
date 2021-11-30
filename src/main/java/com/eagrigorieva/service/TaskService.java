package com.eagrigorieva.service;

import com.eagrigorieva.dto.TaskDto;
import com.eagrigorieva.model.Task;
import com.eagrigorieva.operation.*;
import com.eagrigorieva.storage.TaskStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Component
public class TaskService {
    @Autowired
    private TaskStorage taskList;

    public Task create(String description){
        return new Create().execute(taskList, Collections.singletonList(description)).get(0);
    }

    public List<Task> getList(String printMod) {
        return new Print().execute(taskList, Collections.singletonList(printMod));
    }

    public List<Task> deleteTask(String id) {
        return new Delete().execute(taskList, Collections.singletonList(id));
    }

    public List<Task> editTask(String id, String description) {
        return new Edit().execute(taskList, Arrays.asList(id, description));
    }

    public List<Task> toggleTask(String id) {
        return new Toggle().execute(taskList, Collections.singletonList(id));
    }
}
