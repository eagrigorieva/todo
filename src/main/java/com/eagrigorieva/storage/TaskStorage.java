package com.eagrigorieva.storage;

import com.eagrigorieva.model.Task;

import java.util.List;

public interface TaskStorage {
    void add(Task task);

    Task get(int id);

    List<Task> getTaskList();

    void remove(int id);

    int size();

    int getId(Task task);
}
