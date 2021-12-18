package com.eagrigorieva.controller;

import com.eagrigorieva.dto.CreateRequestTaskDto;
import com.eagrigorieva.dto.TaskDto;
import com.eagrigorieva.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Secured({"ROLE_ADMIN", "ROLE_USER"})
@RestController
@RequestMapping("tasks")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @PostMapping
    public TaskDto create(@RequestBody CreateRequestTaskDto request, Authentication authentication) {
        return taskService.create(request.getDescription(), authentication.getName());
    }

    @GetMapping
    public List<TaskDto> getList(@RequestParam(value = "printMod", required = false) String printMod, Authentication authentication) {
        return taskService.getList(printMod, authentication.getName());
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable(value = "id") Long id, Authentication authentication) {
        taskService.deleteTask(id, authentication.getName());
    }

    @PatchMapping("/{id}")
    public TaskDto editTask(@PathVariable(value = "id") Long id, @RequestParam(value = "description") String description, Authentication authentication) {
        return taskService.editTask(id, description, authentication.getName());
    }

    @PostMapping("/{id}/toggle")
    public TaskDto toggleTask(@PathVariable(value = "id") Long id, Authentication authentication) {
        return taskService.toggleTask(id, authentication.getName());
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/all")
    public List<TaskDto> getAllTasks() {
        return taskService.getAllTasks();
    }
}
