package com.eagrigorieva.storage;

import com.eagrigorieva.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TaskStorageRepository implements TaskStorage {

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public void save(Task task) {
        taskRepository.save(task);
    }

    @Override
    public Task get(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(NullPointerException::new);
    }

    @Override
    public List<Task> getTaskList() {
        return taskRepository.findAll();
    }

    @Override
    public boolean remove(Long id) {
        try {
            taskRepository.deleteById(id);
        } catch (EmptyResultDataAccessException ex) {
            return false;
        }
        return true;
    }

    @Override
    public int size() {
        return (int) taskRepository.count();
    }
}
