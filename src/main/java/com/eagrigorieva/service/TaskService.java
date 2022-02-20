package com.eagrigorieva.service;

import com.eagrigorieva.dto.TaskDto;

public interface TaskService {
    TaskDto create(String description, String userName);

    void deleteTask(Long id, String userName);
}
