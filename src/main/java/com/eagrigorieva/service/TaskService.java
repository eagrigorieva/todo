package com.eagrigorieva.service;

import com.eagrigorieva.dto.TaskDto;

import java.util.List;

public interface TaskService {
    TaskDto create(String description, String userName);

    List<TaskDto> getList(String printMod, String userName);

    void deleteTask(String id, String userName);

    TaskDto editTask(String id, String description, String userName);

    List<TaskDto> getAllTasks();

    TaskDto toggleTask(String id, String userName);

}
