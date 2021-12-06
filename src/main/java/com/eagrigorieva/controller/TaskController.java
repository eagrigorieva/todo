package com.eagrigorieva.controller;

import com.eagrigorieva.dto.CreateRequestDto;
import com.eagrigorieva.dto.TaskDto;
import com.eagrigorieva.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("tasks")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @PostMapping
    public TaskDto create(@RequestBody CreateRequestDto createRequestDto) {
        return taskService.create(createRequestDto.getDescription());
    }

    @GetMapping
    public List<TaskDto> getList(@RequestParam(value = "printMod", required = false) String printMod) {
        return taskService.getList(printMod);
    }

    @DeleteMapping("/{id}")
    public List<TaskDto> deleteTask(@PathVariable(value = "id") String id) {
        return taskService.deleteTask(id);
    }

    @PatchMapping("/{id}")
    public List<TaskDto> editTask(@PathVariable(value = "id") String id, @RequestParam(value = "description") String description) {
        return taskService.editTask(id, description);
    }

    @PostMapping("/{id}/toggle")
    public List<TaskDto> toggleTask(@PathVariable(value = "id") String id) {
        return taskService.toggleTask(id);
    }
}
