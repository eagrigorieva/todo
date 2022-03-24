package com.eagrigorieva.service;

import com.eagrigorieva.dto.TaskDto;

import java.util.List;

public interface SyncTaskService extends TaskService{
    List<TaskDto> getAllTasks();
    List<TaskDto> getList(String printMod, String userName);
}
