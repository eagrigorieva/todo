package com.eagrigorieva.mapper;

import com.eagrigorieva.dto.TaskDto;
import com.eagrigorieva.model.Task;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskMapper {
    public TaskDto mapToTaskDto(Task task) {
        TaskDto dto = new TaskDto();
        dto.setId(String.valueOf(task.getId()));
        dto.setDescription(task.getDescription());
        dto.setTaskStatus(task.getTaskStatus());
        return dto;
    }

    public List<TaskDto> mapToListDto(List<Task> taskList) {
        return taskList.stream()
                .map(this::mapToTaskDto)
                .collect(Collectors.toList());
    }
}
