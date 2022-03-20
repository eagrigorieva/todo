package com.eagrigorieva.service.impl;

import com.eagrigorieva.dto.TaskDto;
import com.eagrigorieva.enumeration.PrintMod;
import com.eagrigorieva.service.CompositeTaskService;
import com.eagrigorieva.service.CustomTaskService;
import com.eagrigorieva.service.TaskService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

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
        List<TaskDto> taskDtoList = new ArrayList<>();
        for (CustomTaskService taskService : taskServices) {
            try {
                taskDtoList.addAll(taskService.getList(printMod, userName));
            } catch (Exception e) {
                log.error("Error for getting tasks", e);
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
        List<TaskDto> taskDtoList = new ArrayList<>();
        for (CustomTaskService taskService : taskServices) {
            try {
                taskDtoList.addAll(taskService.getAllTasks());
            } catch (Exception e) {
                log.error("Error for getting tasks", e);
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
