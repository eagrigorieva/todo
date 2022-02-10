package com.eagrigorieva;

import com.eagrigorieva.dto.TaskDto;
import com.eagrigorieva.enumeration.TaskStatus;
import com.eagrigorieva.exception.EntityNotFoundException;
import com.eagrigorieva.mapper.TaskMapper;
import com.eagrigorieva.model.Task;
import com.eagrigorieva.model.User;
import com.eagrigorieva.service.TaskService;
import com.eagrigorieva.storage.TaskRepository;
import com.eagrigorieva.storage.UserRepository;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static com.eagrigorieva.enumeration.PrintMod.COMPLETED;
import static com.eagrigorieva.step.TodoSteps.getTask;
import static com.eagrigorieva.step.TodoSteps.getUser;
import static org.junit.Assert.assertEquals;

@Feature(value = "МКС: todoList")
@Story(value = "Проверки методов TaskService ")
@Log4j2
@RunWith(MockitoJUnitRunner.class)
public class TodoTaskServiceTests {
    private String userName;
    private String password;
    private Long id;
    private User user;
    private Task task;
    private String description;
    private TaskService taskService;

    @Captor
    private ArgumentCaptor<Task> taskCaptor;
    private final TaskMapper mapperMock = new TaskMapper();
    @Mock
    private UserRepository userRepositoryMock;
    @Mock
    private TaskRepository taskRepositoryMock;

    @Before
    public void precondition() {
        userName = RandomStringUtils.randomAlphabetic(4);
        password = RandomStringUtils.randomNumeric(4);
        id = Long.parseLong(RandomStringUtils.randomNumeric(4));
        user = getUser(id, userName, password);
        description = RandomStringUtils.randomAlphabetic(10);
        taskService = new TaskService();
        taskService.setUserRepository(userRepositoryMock);
        taskService.setTaskRepository(taskRepositoryMock);
        taskService.setMapper(mapperMock);
    }

    @Test
    public void successCreateTest() {
        task = new Task();
        taskCaptor = ArgumentCaptor.forClass(Task.class);
        Mockito.when(userRepositoryMock.findByUsername(userName)).thenReturn(user);

        TaskDto result = taskService.create(description, userName);

        assertEquals(description, result.getDescription());
        assertEquals(TaskStatus.CREATED, result.getTaskStatus());
        Mockito.verify(taskRepositoryMock, Mockito.times(1)).save(taskCaptor.capture());
        Mockito.verify(userRepositoryMock, Mockito.times(1)).findByUsername(userName);
    }

    @Test
    public void successDeleteTest() {
        task = getTask(id, description, TaskStatus.CREATED, user);
        Mockito.when(userRepositoryMock.findByUsername(userName)).thenReturn(user);
        Mockito.when(taskRepositoryMock.findByIdAndUser(id, user)).thenReturn(java.util.Optional.ofNullable(task));

        taskService.deleteTask(id, userName);

        Mockito.verify(userRepositoryMock, Mockito.times(1)).findByUsername(userName);
        Mockito.verify(taskRepositoryMock, Mockito.times(1)).findByIdAndUser(id, user);
        Mockito.verify(taskRepositoryMock, Mockito.times(1)).deleteById(id);

    }

    @Test
    public void errorDeleteUserHasntTaskTest() {
        task = getTask(id, description, TaskStatus.CREATED, user);
        Mockito.when(userRepositoryMock.findByUsername(userName)).thenReturn(user);

        try {
            taskService.editTask(id, description, userName);
        } catch (EntityNotFoundException ex) {
            Mockito.verify(userRepositoryMock, Mockito.times(1)).findByUsername(userName);
            Mockito.verify(taskRepositoryMock, Mockito.times(1)).findByIdAndUser(id, user);
            Mockito.verify(taskRepositoryMock, Mockito.times(0)).deleteById(id);
        }
    }

    @Test
    public void errorDeleteUserNotFoundTest() {
        task = getTask(id, description, TaskStatus.CREATED, user);

        try {
            taskService.editTask(id, description, userName);
        } catch (EntityNotFoundException ex) {
            Mockito.verify(userRepositoryMock, Mockito.times(1)).findByUsername(userName);
            Mockito.verify(taskRepositoryMock, Mockito.times(0)).findByIdAndUser(id, user);
            Mockito.verify(taskRepositoryMock, Mockito.times(0)).deleteById(id);
        }
    }

    @Test
    public void successEditTest() {
        task = getTask(id, description, TaskStatus.CREATED, user);
        Mockito.when(userRepositoryMock.findByUsername(userName)).thenReturn(user);
        Mockito.when(taskRepositoryMock.findByIdAndUser(id, user)).thenReturn(java.util.Optional.ofNullable(task));
        Mockito.when(taskRepositoryMock.save(task)).thenReturn(task);

        taskService.editTask(id, description, userName);

        Mockito.verify(userRepositoryMock, Mockito.times(1)).findByUsername(userName);
        Mockito.verify(taskRepositoryMock, Mockito.times(1)).findByIdAndUser(id, user);
        Mockito.verify(taskRepositoryMock, Mockito.times(1)).save(task);
    }

    @Test
    public void errorEditUserHasntTaskTest() {
        task = getTask(id, description, TaskStatus.CREATED, user);
        Mockito.when(userRepositoryMock.findByUsername(userName)).thenReturn(user);

        try {
            taskService.editTask(id, description, userName);
        } catch (EntityNotFoundException ex) {
            Mockito.verify(userRepositoryMock, Mockito.times(1)).findByUsername(userName);
            Mockito.verify(taskRepositoryMock, Mockito.times(1)).findByIdAndUser(id, user);
            Mockito.verify(taskRepositoryMock, Mockito.times(0)).save(task);
        }
    }

    @Test
    public void errorEditUserNotFoundTest() {
        task = getTask(id, description, TaskStatus.CREATED, user);

        try {
            taskService.editTask(id, description, userName);
        } catch (EntityNotFoundException ex) {
            Mockito.verify(userRepositoryMock, Mockito.times(1)).findByUsername(userName);
            Mockito.verify(taskRepositoryMock, Mockito.times(0)).findByIdAndUser(id, user);
            Mockito.verify(taskRepositoryMock, Mockito.times(0)).save(task);
        }
    }

    @Test
    public void successToggleTest() {
        task = getTask(id, description, TaskStatus.CREATED, user);
        Mockito.when(userRepositoryMock.findByUsername(userName)).thenReturn(user);
        Mockito.when(taskRepositoryMock.findByIdAndUser(id, user)).thenReturn(java.util.Optional.ofNullable(task));
        Mockito.when(taskRepositoryMock.save(task)).thenReturn(task);

        TaskDto result = taskService.toggleTask(id, userName);
        assertEquals(description, result.getDescription());
        assertEquals(TaskStatus.COMPLETED, result.getTaskStatus());

        Mockito.verify(userRepositoryMock, Mockito.times(1)).findByUsername(userName);
        Mockito.verify(taskRepositoryMock, Mockito.times(1)).findByIdAndUser(id, user);
        Mockito.verify(taskRepositoryMock, Mockito.times(1)).save(task);
    }

    @Test
    public void errorToggleUserHasntTaskTest() {
        task = getTask(id, description, TaskStatus.CREATED, user);
        Mockito.when(userRepositoryMock.findByUsername(userName)).thenReturn(user);

        try {
            taskService.toggleTask(id, userName);
        } catch (EntityNotFoundException ex) {
            Mockito.verify(userRepositoryMock, Mockito.times(1)).findByUsername(userName);
            Mockito.verify(taskRepositoryMock, Mockito.times(1)).findByIdAndUser(id, user);
            Mockito.verify(taskRepositoryMock, Mockito.times(0)).save(task);
        }
    }

    @Test
    public void errorToggleUserNotFoundTest() {
        task = getTask(id, description, TaskStatus.CREATED, user);

        try {
            taskService.toggleTask(id, userName);
        } catch (EntityNotFoundException ex) {
            Mockito.verify(userRepositoryMock, Mockito.times(1)).findByUsername(userName);
            Mockito.verify(taskRepositoryMock, Mockito.times(0)).findByIdAndUser(id, user);
            Mockito.verify(taskRepositoryMock, Mockito.times(0)).save(task);
        }
    }

    @Test
    public void successGetListTest() {
        List<Task> taskList = new ArrayList<>();
        taskList.add(getTask(0, description, TaskStatus.CREATED, user));
        taskList.add(getTask(1, description, TaskStatus.COMPLETED, user));
        taskList.add(getTask(2, description, TaskStatus.COMPLETED, user));
        Mockito.when(taskRepositoryMock.findAllByUser(user)).thenReturn(taskList);
        Mockito.when(userRepositoryMock.findByUsername(userName)).thenReturn(user);

        List<TaskDto> result = taskService.getList(COMPLETED.name(), userName);
        int count = (int) result.stream().filter(f -> f.getTaskStatus().equals(TaskStatus.COMPLETED)).count();

        assertEquals(count, result.size());
        Mockito.verify(userRepositoryMock, Mockito.times(1)).findByUsername(userName);
        Mockito.verify(taskRepositoryMock, Mockito.times(1)).findAllByUser(user);
    }
}
