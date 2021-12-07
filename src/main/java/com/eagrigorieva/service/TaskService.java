package com.eagrigorieva.service;

import com.eagrigorieva.dto.TaskDto;
import com.eagrigorieva.operation.*;
import com.eagrigorieva.storage.TaskStorage;
import com.eagrigorieva.mapper.TaskMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Component
public class TaskService {
    @Autowired
    private TaskStorage taskList;
    @Autowired
    private TaskMapper mapper;

    public TaskDto create(String description){
        return mapper.mapToListDto(new Create().execute(taskList, Collections.singletonList(description))).get(0);
    }

    public List<TaskDto> getList(String printMod) {
        return mapper.mapToListDto(new Print().execute(taskList, Collections.singletonList(printMod)));
    }

    public List<TaskDto> deleteTask(String id) {
        return mapper.mapToListDto(new Delete().execute(taskList, Collections.singletonList(id)));
    }

    public List<TaskDto> editTask(String id, String description) {
        return mapper.mapToListDto(new Edit().execute(taskList, Arrays.asList(id, description)));
    }

    public List<TaskDto> toggleTask(String id) {
        return mapper.mapToListDto(new Toggle().execute(taskList, Collections.singletonList(id)));
    }
}
