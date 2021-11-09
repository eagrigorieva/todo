package com.eagrigorieva.model;

import java.util.List;

public interface TaskStorage {
    void add(Task task);
    Task get(int id);
    List<Task> getTaskList();
    void remove(int id);
    int size();
    int indexOf(Task task);
}
