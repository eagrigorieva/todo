package com.eagrigorieva.integration;

import com.eagrigorieva.config.FeignConfiguration;
import com.eagrigorieva.dto.IntegrationTaskDto;
import com.eagrigorieva.dto.TaskDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@FeignClient(name="ToDoList", url = "${integration.url}", path = "/customer/tasks/", configuration = FeignConfiguration.class)
public interface IntegrationTaskClient {

    @DeleteMapping("{id}")
    void deleteTask(@PathVariable() String id);

    @PatchMapping("{id}/modification")
    void editTask(@RequestParam(value = "newDescription") String newDescription,
                         @PathVariable String id);
    @GetMapping
    List<IntegrationTaskDto> getTasks(@RequestParam(name = "isAll") boolean isAll);

    @PatchMapping("{id}/completed")
    void toggleTask(@PathVariable String id);
}
