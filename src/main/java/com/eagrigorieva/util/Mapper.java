package com.eagrigorieva.util;

import com.eagrigorieva.dto.TaskDto;
import com.eagrigorieva.model.Task;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class Mapper {
    public TaskDto mapToTaskDto(Task task){
        TaskDto dto = new TaskDto();
        dto.setId(task.getId());
        dto.setDescription(task.getDescription());
        dto.setTaskStatus(task.getTaskStatus());
        return dto;
    }//TODO подумать как сделать лучше конструктор?

    public Task mapToTask(TaskDto dto){
        return new Task(dto.getId(), dto.getDescription(), dto.getTaskStatus());
    }

    public List<TaskDto> mapToListDto(List<Task> taskList){
        return taskList.stream()
                .map(this::mapToTaskDto)
                .collect(Collectors.toList());
    }
}