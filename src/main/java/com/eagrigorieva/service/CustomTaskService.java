package com.eagrigorieva.service;

public interface CustomTaskService extends AsyncTaskService{
    boolean supports(String taskId);
}
