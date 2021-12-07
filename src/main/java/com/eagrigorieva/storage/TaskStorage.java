package com.eagrigorieva.storage;

import com.eagrigorieva.model.Task;

import java.util.List;

public interface TaskStorage {
    void save(Task task);

    Task get(Long id);

    List<Task> getTaskList();

    boolean remove(Long id);

    int size();
}
