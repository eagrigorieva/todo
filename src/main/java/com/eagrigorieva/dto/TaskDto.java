package com.eagrigorieva.dto;

import com.eagrigorieva.enumeration.TaskStatus;
import lombok.Data;

@Data
public class TaskDto {
    private Long id;
    private String description;
    private TaskStatus taskStatus;
}
