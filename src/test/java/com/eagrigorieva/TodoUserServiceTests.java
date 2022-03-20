package com.eagrigorieva;

import com.eagrigorieva.exception.EntityNotFoundException;
import com.eagrigorieva.mapper.UserMapper;
import com.eagrigorieva.model.User;
import com.eagrigorieva.service.impl.UserServiceImpl;
import com.eagrigorieva.storage.UserRepository;
import com.eagrigorieva.storage.UserRoleRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import static com.eagrigorieva.step.TodoSteps.getUser;
import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class TodoUserServiceTests {
    private String userName;
    private String password;
    private Long id;
    private User user;
    private UserServiceImpl userService;
    @Mock
    private UserMapper mapperMock;
    @Mock
    private UserRepository userRepositoryMock;
    @Mock
    private UserRoleRepository userRoleRepositoryMock;
    @Mock
    private PasswordEncoder passwordEncoderMock;

    @Before
    public void precondition() {
        userName = RandomStringUtils.randomAlphabetic(4);
        password = RandomStringUtils.randomNumeric(4);
        id = Long.parseLong(RandomStringUtils.randomNumeric(4));

        userService = new UserServiceImpl();

        userService.setUserRepository(userRepositoryMock);
        userService.setUserRoleRepository(userRoleRepositoryMock);
        userService.setMapper(mapperMock);
        userService.setPasswordEncoder(passwordEncoderMock);
    }

    @Test
    public void successCreateTest() {
        userService.create(password, userName, "1");

        Mockito.verify(userRepositoryMock, Mockito.times(1)).save(Mockito.any());
        Mockito.verify(userRoleRepositoryMock, Mockito.times(1)).findByCode("1");
    }

    @Test
    public void successLoadUserByUserNameTest() {
        user = getUser(id, userName, password);
        Mockito.when(userRepositoryMock.findByUsername(userName)).thenReturn(user);

        UserDetails result = userService.loadUserByUsername(userName);

        Mockito.verify(userRepositoryMock, Mockito.times(1)).findByUsername(userName);
        assertEquals(userName, result.getUsername());
        assertEquals(password, result.getPassword());
    }

    @Test
    public void errorLoadUserByUserNameTest() {
        try {
            userService.loadUserByUsername(userName);
        } catch (UsernameNotFoundException ex) {
            assertEquals("User not found", ex.getMessage());
            Mockito.verify(userRepositoryMock, Mockito.times(1)).findByUsername(userName);
        }
    }

    @Test
    public void successDeleteTest() {
        user = getUser(id, userName, password);
        Mockito.when(userRepositoryMock.findById(id)).thenReturn(java.util.Optional.of(user));

        userService.delete(id);

        Mockito.verify(userRepositoryMock, Mockito.times(1)).findById(id);
        Mockito.verify(userRepositoryMock, Mockito.times(1)).deleteById(id);
    }

    @Test
    public void errorDeleteTest() {
        try {
            userService.delete(id);
        } catch (EntityNotFoundException ex) {
            Mockito.verify(userRepositoryMock, Mockito.times(1)).findById(id);
            Mockito.verify(userRepositoryMock, Mockito.times(0)).deleteById(id);
        }
    }
}
