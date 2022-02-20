package com.eagrigorieva;

import com.eagrigorieva.enumeration.TaskStatus;
import com.eagrigorieva.model.Task;
import com.eagrigorieva.model.User;
import com.eagrigorieva.model.UserRole;
import com.eagrigorieva.storage.TaskRepository;
import com.eagrigorieva.storage.UserRepository;
import com.eagrigorieva.storage.UserRoleRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.containers.PostgreSQLContainer;

import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@DataJpaTest
public class TodoDaoH2Tests {

    public static final String DEFAULT_CODE = "1";

    private String name;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Before
    public void precondition() {
        name = RandomStringUtils.randomAlphabetic(10);
    }

    @Test
    public void addUserRoleTest() {
        addUserRole(DEFAULT_CODE);
        UserRole userRole = addUserRole("2");
        Assert.assertEquals(userRole.getId(), userRoleRepository.findByCode("2").getId());
    }

    @Test
    public void addUserTest() {
        User user = addUser(RandomStringUtils.randomAlphabetic(10), DEFAULT_CODE);
        Assert.assertTrue(userRepository.existsById(user.getId()));
    }

    @Test
    public void deletedUserTest() {
        User user = addUser(name, DEFAULT_CODE);
        userRepository.delete(user);

        Assert.assertFalse(userRepository.existsById(user.getId()));
    }

    @Test
    public void createTaskTest() {
        User user = addUser(name, DEFAULT_CODE);
        Task task = addTask(user);

        Assert.assertTrue(taskRepository.existsById(task.getId()));
    }

    @Test
    public void deleteTaskTest() {
        User user = addUser(name, DEFAULT_CODE);
        Task task = addTask(user);
        taskRepository.delete(task);
        Assert.assertFalse(taskRepository.existsById(task.getId()));
    }

    @Test
    public void findTaskForUserTest() {
        User user = addUser(name, DEFAULT_CODE);
        Task task = addTask(user);

        List<Task> foundedTasks = taskRepository.findAllByUser(user);

        Assert.assertEquals(1, foundedTasks.size());
        Assert.assertEquals(foundedTasks.get(0).getId(), task.getId());
    }

    @Test
    public void findTaskForOtherUserTest() {
        User user = addUser(name, DEFAULT_CODE);
        User otherUser = addUser(name + "1", DEFAULT_CODE);
        addTask(user);

        List<Task> foundedTasks = taskRepository.findAllByUser(otherUser);

        Assert.assertEquals(0, foundedTasks.size());
    }

    @Test
    public void findTaskByUserAndTaskIdTest() {
        User user = addUser(name, DEFAULT_CODE);
        Task task = addTask(user);

        Optional<Task> foundedTask = taskRepository.findByIdAndUser(task.getId(), user);

        Assert.assertTrue(foundedTask.isPresent());
    }

    @Test
    public void findTaskByOtherUserAndTaskIdTest() {
        User user = addUser(name, DEFAULT_CODE);
        User otherUser = addUser(name + "1", DEFAULT_CODE);
        Task task = addTask(user);

        Optional<Task> foundedTask = taskRepository.findByIdAndUser(task.getId(), otherUser);

        Assert.assertFalse(foundedTask.isPresent());
    }

    @Test
    public void findTaskByUserAndOtherTaskIdTest() {
        User user = addUser(name, DEFAULT_CODE);
        addTask(user);

        User otherUser = addUser(name + "1", DEFAULT_CODE);
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