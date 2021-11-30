package com.eagrigorieva.dto;

import com.eagrigorieva.enumeration.TaskStatus;
import lombok.Data;

@Data
public class TaskDto {
    private String id;
    private String description;
    private TaskStatus taskStatus;
}
