package com.eagrigorieva.service.impl;

import com.eagrigorieva.dto.TaskDto;
import com.eagrigorieva.service.CompositeTaskService;
import com.eagrigorieva.service.CustomTaskService;
import com.eagrigorieva.service.TaskService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@Log4j2
@Component
public class CompositeTaskServiceImpl implements CompositeTaskService {

    @Autowired
    private List<CustomTaskService> taskServices;

    @Override
    public TaskDto create(String description, String userName) {
        return getTaskService(null).create(description, userName);
    }

    @Override
    public List<TaskDto> getList(String printMod, String userName) {
        List<Future<List<TaskDto>>> futures = new ArrayList<>();
        for (CustomTaskService taskService : taskServices) {
            try {
                futures.add(taskService.getList(printMod, userName));
            } catch (Exception e) {
                log.error("Error for getting tasks", e);
            }
        }

        List<TaskDto> taskDtoList = new ArrayList<>();
        for (Future<List<TaskDto>> future : futures) {
            try {
                taskDtoList.addAll(future.get());
            } catch (InterruptedException | ExecutionException e) {
                log.error("Error for async getting tasks", e);
            }
        }
        return taskDtoList;
    }

    @Override
    public void deleteTask(String id, String userName) {
        getTaskService(id).deleteTask(id, userName);
    }

    @Override
    public TaskDto editTask(String id, String description, String userName) {
        return getTaskService(id).editTask(id, description, userName);
    }

    @Override
    public List<TaskDto> getAllTasks() {
        List<Future<List<TaskDto>>> futures = new ArrayList<>();
        for (CustomTaskService taskService : taskServices) {
            try {
                futures.add(taskService.getAllTasks());
            } catch (Exception e) {
                log.error("Error for getting tasks", e);
            }
        }

        List<TaskDto> taskDtoList = new ArrayList<>();
        for (Future<List<TaskDto>> future : futures) {
            try {
                taskDtoList.addAll(future.get());
            } catch (InterruptedException | ExecutionException e) {
                log.error("Error for async getting tasks", e);
            }
        }
        return taskDtoList;
    }

    @Override
    public TaskDto toggleTask(String id, String userName) {
        return getTaskService(id).toggleTask(id, userName);
    }

    private TaskService getTaskService(String id) {
        for (CustomTaskService service : taskServices) {
            if (service.supports(id)) {
                return service;
            }
        }
        throw new UnsupportedOperationException();
    }
}
