package com.eagrigorieva.controller;

import com.eagrigorieva.dto.CreateRequestDto;
import com.eagrigorieva.dto.TaskDto;
import com.eagrigorieva.model.Task;
import com.eagrigorieva.operation.*;
import com.eagrigorieva.storage.TaskStorage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.eagrigorieva.util.Mapper;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("tasks")
@RequiredArgsConstructor
public class CommandController {
    @Autowired
    private TaskStorage taskList;
    @Autowired
    private Mapper mapper;

    @PostMapping
    public TaskDto create(@RequestBody CreateRequestDto createRequestDto) {
        Create create = new Create();
        List<Task> result = create.execute(taskList, Collections.singletonList(createRequestDto.getDescription()));
        return mapper.mapToTaskDto(result.get(0));
    }

    @GetMapping
    public List<TaskDto> getList(@RequestParam(value = "printMod", required = false) String printMod) {
        Print print = new Print();
        List<Task> result = print.execute(taskList, Collections.singletonList(printMod));
        return mapper.mapToListDto(result);
    }

    @DeleteMapping("/{id}")
    public List<TaskDto> deleteTask(@PathVariable(value = "id") String id) {
        Delete delete = new Delete();
        List<Task> result = delete.execute(taskList, Collections.singletonList(id));
        return mapper.mapToListDto(result);
    }

    @PatchMapping("/{id}")
    public List<TaskDto> editTask(@PathVariable(value = "id") String id, @RequestParam(value = "description") String description) {
        Edit edit = new Edit();
        List<Task> result = edit.execute(taskList, Arrays.asList(id, description));
        return mapper.mapToListDto(result);
    }

    @PostMapping("/{id}/toggle")
    public List<TaskDto> toggleTask(@PathVariable(value = "id") String id) {
        Toggle toggle = new Toggle();
        List<Task> result = toggle.execute(taskList, Collections.singletonList(id));
        return mapper.mapToListDto(result);
    }

    @GetMapping("/search")
    public List<TaskDto> search(@RequestParam(value = "query") String query) {
        Search search = new Search();
        List<Task> result = search.execute(taskList, Collections.singletonList(query));
        return mapper.mapToListDto(result);
    }
}
