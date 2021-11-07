package com.eagrigorieva.model;

import com.eagrigorieva.enumeration.TaskStatus;
import lombok.Data;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Data
public class Task {

    private String id;
    private String description;
    private TaskStatus taskStatus;

    public Task(String id, String description, TaskStatus taskStatus) {
        this.id = id;
        this.description = description;
        this.taskStatus = taskStatus;
    }
}
