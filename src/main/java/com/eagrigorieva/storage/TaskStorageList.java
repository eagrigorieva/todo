package com.eagrigorieva.storage;

import com.eagrigorieva.model.Task;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class TaskStorageList implements TaskStorage {

    private final List<Task> taskList = new ArrayList<>();

    @Override
    public void add(Task task) {
        taskList.add(task);
    }

    @Override
    public Task get(String id) {
        return taskList.stream()
                .filter(t -> Objects.equals(t.getId(), id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Task> getTaskList() {
        return taskList;
    }

    @Override
    public boolean remove(String id) {
        return taskList.removeIf(t -> Objects.equals(t.getId(), id));
    }

    @Override
    public int size() {
        return taskList.size();
    }
}
