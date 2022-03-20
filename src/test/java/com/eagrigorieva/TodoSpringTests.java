package com.eagrigorieva;

import com.eagrigorieva.service.TaskService;
import com.eagrigorieva.service.impl.TaskServiceImpl;
import com.eagrigorieva.service.UserService;
import com.eagrigorieva.service.impl.UserServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TodoSpringTests {

    @Autowired
    private UserService userService;

    @Autowired
    private TaskService taskService;

    @Test
    public void contextLoads() {
    }

    @Test
    public void checkUserService() {
        Assert.assertTrue(userService instanceof UserServiceImpl);
    }

    @Test
    public void checkTaskService() {
        Assert.assertTrue(taskService instanceof TaskServiceImpl);
    }
}
