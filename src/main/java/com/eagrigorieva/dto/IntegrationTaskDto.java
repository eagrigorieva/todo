package com.eagrigorieva.dto;

import com.eagrigorieva.enumeration.TaskStatus;
import lombok.Data;

@Data
public class IntegrationTaskDto {
    private String id;
    private String description;
    private boolean taskStatus;
}
