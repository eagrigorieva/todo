package com.eagrigorieva.step;

import com.eagrigorieva.enumeration.TaskStatus;
import com.eagrigorieva.model.Task;
import com.eagrigorieva.model.User;
import com.eagrigorieva.model.UserRole;

public class TodoSteps {
    public static User getUser(long id, String userName, String password) {
        User user = new User();
        user.setId(id);
        user.setUsername(userName);
        user.setPassword(password);
        user.setRole(new UserRole());
        return user;
    }

    public static Task getTask(long id, String description, TaskStatus taskStatus, User user) {
        Task task = new Task();
        task.setId(id);
        task.setTaskStatus(taskStatus);
        task.setDescription(description);
        task.setUser(user);
        return task;
    }
}
