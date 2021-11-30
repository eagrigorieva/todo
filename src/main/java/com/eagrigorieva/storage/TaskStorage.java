package com.eagrigorieva.storage;

import com.eagrigorieva.model.Task;

import java.util.List;

public interface TaskStorage {
    void add(Task task);

    Task get(String id);

    List<Task> getTaskList();

    boolean remove(String id);

    int size();
}
