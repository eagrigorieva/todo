package com.eagrigorieva;

import com.eagrigorieva.model.User;
import com.eagrigorieva.model.UserRole;
import com.eagrigorieva.storage.UserRepository;
import com.eagrigorieva.storage.UserRoleRepository;
import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.containers.PostgreSQLContainer;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(initializers = {TodoDaoTestContainersTests.Initializer.class})
public class TodoDaoTestContainersTests {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @ClassRule
    public static PostgreSQLContainer postgreSQLContainer = new PostgreSQLContainer("postgres:11.1")
            .withDatabaseName("integration-tests-db")
            .withUsername("sa")
            .withPassword("sa");

    static class Initializer
            implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
            TestPropertyValues.of(
                    "spring.datasource.url=" + postgreSQLContainer.getJdbcUrl(),
                    "spring.datasource.username=" + postgreSQLContainer.getUsername(),
                    "spring.datasource.password=" + postgreSQLContainer.getPassword(),
                    "spring.datasource.driver-class-name=org.postgresql.Driver",
                    "spring.jpa.hibernate.ddl-auto=create-drop"
            ).applyTo(configurableApplicationContext.getEnvironment());
        }
    }

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
