package com.eagrigorieva.model;

import java.util.ArrayList;
import java.util.List;

public class TaskStorageImpl implements TaskStorage {

    private final List<Task> taskList = new ArrayList<>();

    @Override
    public void add(Task task) {
        taskList.add(task);
    }

    @Override
    public Task get(int id) {
        return taskList.get(id);
    }

    @Override
    public List<Task> getTaskList() {
        return taskList;
    }

    @Override
    public void remove(int id) {
        taskList.remove(id);
    }

    @Override
    public int size() {
        return taskList.size();
    }

    @Override
    public int indexOf(Task task) {
        return taskList.indexOf(task);
    }
}
