package com.eagrigorieva;

import com.eagrigorieva.model.User;
import com.eagrigorieva.model.UserRole;
import com.eagrigorieva.storage.UserRepository;
import com.eagrigorieva.storage.UserRoleRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class TodoDaoH2Tests {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Test
    public void testDao() {
        Assert.assertEquals(0, userRepository.findAll().size());
    }

    @Test
    public void test() {
        UserRole role = new UserRole();
        role.setCode("1");
        userRoleRepository.save(role);

        User user = new User();
        user.setUsername("jop");
        user.setPassword("jop");
        user.setRole(userRoleRepository.findByCode("1"));

        userRepository.save(user);
        Assert.assertEquals(1, userRepository.findAll().size());
    }
}
