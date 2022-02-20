package com.eagrigorieva;

import com.eagrigorieva.enumeration.TaskStatus;
import com.eagrigorieva.model.Task;
import com.eagrigorieva.model.User;
import com.eagrigorieva.model.UserRole;
import com.eagrigorieva.storage.TaskRepository;
import com.eagrigorieva.storage.UserRepository;
import com.eagrigorieva.storage.UserRoleRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@DataJpaTest
public class TodoDaoH2Tests {

    public static final String DEFAULT_CODE = "1";
    public static final String DEFAULT_NAME = "mars";

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Test
    public void emptyStorageTest() {
        Assert.assertEquals(0, userRepository.findAll().size());
    }

    @Test
    public void addUserRoleTest() {
        UserRole userRole = addUserRole("2");
        Assert.assertEquals(userRole, userRoleRepository.findByCode("2"));
    }

    @Test
    public void addUserTest() {
        addUserRole(DEFAULT_CODE);
        addUser(DEFAULT_NAME, DEFAULT_CODE);
        Assert.assertEquals(1, userRepository.findAll().size());
    }

    @Test
    public void deletedUserTest() {
        addUserRole(DEFAULT_CODE);
        User user = addUser(DEFAULT_NAME, DEFAULT_CODE);
        userRepository.delete(user);

        Assert.assertEquals(0, userRepository.findAll().size());
    }

    @Test
    public void createTaskTest() {
        addUserRole(DEFAULT_CODE);
        User user = addUser(DEFAULT_NAME, DEFAULT_CODE);
        addTask(user);

        Assert.assertEquals(1, taskRepository.findAll().size());
    }

    @Test
    public void findTaskForUserTest() {
        addUserRole(DEFAULT_CODE);
        User user = addUser(DEFAULT_NAME, DEFAULT_CODE);
        Task task = addTask(user);

        List<Task> foundedTasks = taskRepository.findAllByUser(user);

        Assert.assertEquals(1, foundedTasks.size());
        Assert.assertEquals(foundedTasks.get(0), task);
    }

    @Test
    public void findTaskForOtherUserTest() {
        addUserRole(DEFAULT_CODE);
        User user = addUser(DEFAULT_NAME, DEFAULT_CODE);
        User otherUser = addUser(DEFAULT_NAME + "1", DEFAULT_CODE);
        addTask(user);

        List<Task> foundedTasks = taskRepository.findAllByUser(otherUser);

        Assert.assertEquals(0, foundedTasks.size());
    }

    @Test
    public void findTaskByUserAndTaskIdTest() {
        addUserRole(DEFAULT_CODE);
        User user = addUser(DEFAULT_NAME, DEFAULT_CODE);
        Task task = addTask(user);

        Optional<Task> foundedTask = taskRepository.findByIdAndUser(task.getId(), user);

        Assert.assertTrue(foundedTask.isPresent());
    }

    @Test
    public void findTaskByOtherUserAndTaskIdTest() {
        addUserRole(DEFAULT_CODE);
        User user = addUser(DEFAULT_NAME, DEFAULT_CODE);
        User otherUser = addUser(DEFAULT_NAME + "1", DEFAULT_CODE);
        Task task = addTask(user);

        Optional<Task> foundedTask = taskRepository.findByIdAndUser(task.getId(), otherUser);

        Assert.assertFalse(foundedTask.isPresent());
    }

    @Test
    public void findTaskByUserAndOtherTaskIdTest() {
        addUserRole(DEFAULT_CODE);
        User user = addUser(DEFAULT_NAME, DEFAULT_CODE);
        addTask(user);

        User otherUser = addUser(DEFAULT_NAME + "1", DEFAULT_CODE);
        Task otherTask = addTask(otherUser);

        Optional<Task> foundedTask = taskRepository.findByIdAndUser(otherTask.getId(), user);

        Assert.assertFalse(foundedTask.isPresent());
    }

    private Task addTask(User user) {
        Task task = new Task();
        task.setTaskStatus(TaskStatus.CREATED);
        task.setDescription("something");
        task.setUser(user);
        taskRepository.save(task);
        return task;
    }

    private User addUser(String name, String code) {
        User user = new User();
        user.setUsername(name);
        user.setPassword("123");
        user.setRole(userRoleRepository.findByCode(code));
        userRepository.save(user);
        return user;
    }

    private UserRole addUserRole(String code) {
        UserRole role = new UserRole();
        role.setCode(code);
        userRoleRepository.save(role);
        return role;
    }
}