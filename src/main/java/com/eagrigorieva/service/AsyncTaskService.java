package com.eagrigorieva.service;

import com.eagrigorieva.dto.TaskDto;

import java.util.List;
import java.util.concurrent.Future;

public interface AsyncTaskService extends TaskService{
    Future<List<TaskDto>> getAllTasks();
    Future<List<TaskDto>> getList(String printMod, String userName);
}
