package com.eagrigorieva.service;

import com.eagrigorieva.dto.TaskDto;
import com.eagrigorieva.enumeration.PrintMod;
import com.eagrigorieva.enumeration.TaskStatus;
import com.eagrigorieva.exception.EntityNotFoundException;
import com.eagrigorieva.mapper.TaskMapper;
import com.eagrigorieva.model.Task;
import com.eagrigorieva.model.User;
import com.eagrigorieva.storage.TaskRepository;
import com.eagrigorieva.storage.UserRepository;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static com.eagrigorieva.enumeration.TaskStatus.COMPLETED;
import static com.eagrigorieva.enumeration.TaskStatus.CREATED;

@Log4j2
@Setter
@Component
public class TaskServiceImpl implements TaskService {
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private TaskMapper mapper;
    @Autowired
    private UserRepository userRepository;

    @Override
    public TaskDto create(String description, String userName) {
        User fondedUsersInDB = userRepository.findByUsername(userName);
        Task task = new Task();
        task.setDescription(description);
        task.setTaskStatus(CREATED);
        task.setUser(fondedUsersInDB);
        taskRepository.save(task);
        log.debug("Task {} description created", description);
        System.out.println("SUCCESS");
        return mapper.mapToTaskDto(task);
    }

    public List<TaskDto> getList(String printMod, String userName) {
        return mapper.mapToListDto(getTaskList(printMod, userName));
    }

    @Override
    public void deleteTask(Long id, String userName) {
        User fondedUsersInDB = userRepository.findByUsername(userName);
        Task task = taskRepository.findByIdAndUser(id, fondedUsersInDB).orElse(null);
        if (task == null) {
            log.error("Task not found");
            System.out.println("Task not found");
            throw new EntityNotFoundException();
        }
        taskRepository.deleteById(id);
        log.debug("Task is deleted");
        System.out.println("SUCCESS");

    }

    public TaskDto editTask(Long id, String description, String userName) {
        User fondedUsersInDB = userRepository.findByUsername(userName);
        Task task = taskRepository.findByIdAndUser(id, fondedUsersInDB).orElse(null);

        if (task != null) {
            task.setDescription(description);
            taskRepository.save(task);
            System.out.println("SUCCESS");
            log.debug("Task is edited");
            return mapper.mapToTaskDto(task);

        } else {
            log.error("Task not found");
            System.out.println("Task not found");
            throw new EntityNotFoundException();
        }

    }

    public List<TaskDto> getAllTasks() {
        return mapper.mapToListDto(taskRepository.findAll());
    }

    public TaskDto toggleTask(Long id, String userName) {
        User fondedUsersInDB = userRepository.findByUsername(userName);
        Task task = taskRepository.findByIdAndUser(id, fondedUsersInDB).orElse(null);

        if (task != null) {
            task.setTaskStatus(task.getTaskStatus() == CREATED ? COMPLETED : CREATED);
            taskRepository.save(task);
            log.debug("Status changed: {}", task.getTaskStatus().name());
            System.out.println("SUCCESS");
            return mapper.mapToTaskDto(task);

        } else {
            log.error("Task not found");
            System.out.println("Task not found");
            throw new EntityNotFoundException();
        }
    }

    private List<Task> getTaskList(String printMod, String userName) {
        PrintMod modCommand = PrintMod.getPrintMod(printMod);
        User user = userRepository.findByUsername(userName);
        switch (modCommand) {
            case ALL:
                return taskRepository.findAllByUser(user);
            case CREATED:
                return getTaskListByStatus(user, CREATED);
            case COMPLETED:
            default:
                return getTaskListByStatus(user, COMPLETED);
        }
    }

    private List<Task> getTaskListByStatus(User user, TaskStatus status) {
        return taskRepository.findAllByUser(user)
                .stream()
                .filter(t -> t.getTaskStatus() == status)
                .collect(Collectors.toList());
    }
}
