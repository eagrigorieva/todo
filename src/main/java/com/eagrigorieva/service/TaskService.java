package com.eagrigorieva.service;

import com.eagrigorieva.dto.TaskDto;

import java.util.List;

public interface TaskService {
    TaskDto create(String description, String userName);

    List<TaskDto> getList(String printMod, String userName);

    List<TaskDto> getIoList(boolean isAll);

    void deleteTask(Long id, String userName);

    TaskDto editTask(Long id, String description, String userName);

    List<TaskDto> getAllTasks();

    TaskDto toggleTask(Long id, String userName);

}
