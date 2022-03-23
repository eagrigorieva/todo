package com.eagrigorieva.service.impl;

import com.eagrigorieva.dto.TaskDto;
import com.eagrigorieva.enumeration.PrintMod;
import com.eagrigorieva.integration.IntegrationTaskClient;
import com.eagrigorieva.mapper.IntegrationTaskMapper;
import com.eagrigorieva.service.CustomTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.eagrigorieva.mapper.IntegrationTaskMapper.PREFIX;

@Component
public class IntegrationTaskService implements CustomTaskService {

    @Autowired
    private IntegrationTaskClient integrationTaskClient;
    @Autowired
    private IntegrationTaskMapper integrationTaskMapper;

    @Override
    public TaskDto create(String description, String userName) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<TaskDto> getList(String printMod, String userName) {
        PrintMod resolvedPrintMod = PrintMod.getPrintMod(printMod);
        if (resolvedPrintMod == PrintMod.CREATED){
            return integrationTaskMapper.toTaskDto(integrationTaskClient.getTasks(false));
        }
        return integrationTaskMapper.toTaskDto(integrationTaskClient.getTasks(true));
    }

    @Override
    public void deleteTask(String id, String userName) {
        integrationTaskClient.deleteTask(stripId(id));
    }

    @Override
    public TaskDto editTask(String id, String description, String userName) {
        integrationTaskClient.editTask(description, stripId(id));
        return new TaskDto();
    }

    @Override
    public List<TaskDto> getAllTasks() {
        return integrationTaskMapper.toTaskDto(integrationTaskClient.getTasks(true));
    }

    @Override
    public TaskDto toggleTask(String id, String userName) {
        integrationTaskClient.toggleTask(stripId(id));
        return new TaskDto();
    }

    @Override
    public boolean supports(String taskId) {
        return taskId != null && taskId.startsWith(PREFIX);
    }

    private static String stripId(String id) {
        return id.substring(PREFIX.length());
    }
}
