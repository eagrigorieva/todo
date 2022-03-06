package com.eagrigorieva.mapper;

import com.eagrigorieva.dto.TaskDto;
import com.eagrigorieva.enumeration.TaskStatus;
import com.eagrigorieva.io.TaskIo;
import com.eagrigorieva.model.Task;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.eagrigorieva.enumeration.TaskStatus.COMPLETED;
import static com.eagrigorieva.enumeration.TaskStatus.CREATED;

@Service
public class TaskMapper {
    public TaskDto mapToTaskDto(Task task) {
        TaskDto dto = new TaskDto();
        dto.setId(task.getId());
        dto.setDescription(task.getDescription());
        dto.setTaskStatus(task.getTaskStatus());
        return dto;
    }

    public TaskDto mapToTaskDto(TaskIo task) {
        TaskStatus taskStatus = task.isCompleted() ? COMPLETED : CREATED;
        TaskDto dto = new TaskDto();
        dto.setId(task.getId());
        dto.setDescription(task.getDescription());
        dto.setTaskStatus(taskStatus);
        return dto;
    }

    public List<TaskDto> mapIoToListDto(List<TaskIo> taskList) {
        return taskList.stream()
                .map(this::mapToTaskDto)
                .collect(Collectors.toList());
    }

    public List<TaskDto> mapToListDto(List<Task> taskList) {
        return taskList.stream()
                .map(this::mapToTaskDto)
                .collect(Collectors.toList());
    }
}
