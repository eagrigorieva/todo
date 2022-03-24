package com.eagrigorieva.service;

import com.eagrigorieva.dto.TaskDto;

public interface TaskService {
    TaskDto create(String description, String userName);

    void deleteTask(String id, String userName);

    TaskDto editTask(String id, String description, String userName);

    TaskDto toggleTask(String id, String userName);

}
