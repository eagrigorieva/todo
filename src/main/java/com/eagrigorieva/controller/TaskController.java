package com.eagrigorieva.controller;

import com.eagrigorieva.dto.CreateRequestTaskDto;
import com.eagrigorieva.dto.TaskDto;
import com.eagrigorieva.service.TaskService;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Secured({"ROLE_ADMIN", "ROLE_USER"})
@RestController
@RequestMapping("tasks")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TaskDto create(@Valid @RequestBody CreateRequestTaskDto request, Authentication authentication) {
        return taskService.create(request.getDescription(), authentication.getName());
    }

    @GetMapping
    public List<TaskDto> getList(@NotNull @RequestParam(value = "printMod", required = false) String printMod, Authentication authentication) {
        return taskService.getList(printMod, authentication.getName());
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@NotNull @PathVariable(value = "id") Long id, Authentication authentication) {
        taskService.deleteTask(id, authentication.getName());
    }

    @PatchMapping("/{id}")
    public TaskDto editTask(@NotNull @PathVariable(value = "id") Long id, @NotBlank @Size(min = 2, max = 100) @RequestParam(value = "description") String description, Authentication authentication) {
        return taskService.editTask(id, description, authentication.getName());
    }

    @PostMapping("/{id}/toggle")
    public TaskDto toggleTask(@NotNull @PathVariable(value = "id") Long id, Authentication authentication) {
        return taskService.toggleTask(id, authentication.getName());
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/all")
    public List<TaskDto> getAllTasks() {
        return taskService.getAllTasks();
    }
}
