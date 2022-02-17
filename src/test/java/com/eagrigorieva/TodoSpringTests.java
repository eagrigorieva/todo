package com.eagrigorieva;

import com.eagrigorieva.service.UserService;
import com.eagrigorieva.service.UserServiceImpl;
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

    @Test
    public void contextLoads() {
    }

    //TODO сделать для тасок такой же геморрой
    @Test
    public void checkSomething() {
        Assert.assertTrue(userService instanceof UserServiceImpl);
    }
}
