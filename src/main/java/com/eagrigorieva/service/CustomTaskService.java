package com.eagrigorieva.service;

public interface CustomTaskService extends TaskService{
    boolean supports(String taskId);
}
