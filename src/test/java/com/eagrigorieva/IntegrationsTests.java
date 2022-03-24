package com.eagrigorieva;

import com.eagrigorieva.dto.TaskDto;
import com.eagrigorieva.enumeration.PrintMod;
import com.eagrigorieva.service.impl.IntegrationTaskServiceImpl;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import com.github.tomakehurst.wiremock.extension.responsetemplating.ResponseTemplateTransformer;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import lombok.extern.log4j.Log4j2;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;
import java.util.concurrent.ExecutionException;

import static com.eagrigorieva.enumeration.TaskStatus.COMPLETED;
import static com.eagrigorieva.enumeration.TaskStatus.CREATED;
import static com.github.tomakehurst.wiremock.client.WireMock.ok;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

@Log4j2
@SpringBootTest
@WithMockUser("pers")
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class IntegrationsTests {
    @Autowired
    private WebApplicationContext context;
    @Autowired
    private IntegrationTaskServiceImpl integrationTaskService;

    private MockMvc mvc;

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(WireMockConfiguration.options()
            .port(8089)
            .extensions(new ResponseTemplateTransformer(false)));

    @Before
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @Test
    public void successTrueTest() throws ExecutionException, InterruptedException {
        stubFor(WireMock.get("/customer/tasks?isAll=true")
                .willReturn(ok()
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("getTasksIsAllTrue.json")
                ));

        List<TaskDto> dtos = integrationTaskService.getList(PrintMod.ALL.name(), null).get();

        assertEquals(2, dtos.size());
        TaskDto taskOne = dtos.get(0);
        assertEquals(CREATED, taskOne.getTaskStatus());
        assertEquals("MarsTest", taskOne.getDescription());
        assertEquals("I-A3", taskOne.getId());

        TaskDto taskTwo = dtos.get(1);
        assertEquals(COMPLETED, taskTwo.getTaskStatus());
        assertEquals("PersTest", taskTwo.getDescription());
        assertEquals("I-A4", taskTwo.getId());
    }

    @Test
    public void successFalseTest() throws ExecutionException, InterruptedException {
        stubFor(WireMock.get("/customer/tasks?isAll=false")
                .willReturn(ok()
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("getTasksIsAllFalse.json")
                ));

        List<TaskDto> dtos = integrationTaskService.getList(PrintMod.CREATED.name(), null).get();

        assertEquals(1, dtos.size());
        TaskDto taskOne = dtos.get(0);
        assertEquals(CREATED, taskOne.getTaskStatus());
        assertEquals("MarsTest", taskOne.getDescription());
        assertEquals("I-A3", taskOne.getId());
    }
}
