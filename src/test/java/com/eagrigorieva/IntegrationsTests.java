package com.eagrigorieva;

import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import com.github.tomakehurst.wiremock.extension.responsetemplating.ResponseTemplateTransformer;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static com.github.tomakehurst.wiremock.client.WireMock.ok;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static org.hamcrest.Matchers.*;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Log4j2
@SpringBootTest
@WithMockUser("pers")
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class IntegrationsTests {
    public static final String BASE_WIREMOCK_URL = "http://localhost:8089";
    @Autowired
    private WebApplicationContext context;

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
    public void successTrueTest() throws Exception {
        stubFor(WireMock.get("/customer/tasks?isAll=true")
                .willReturn(ok()
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("getTasksIsAllTrue.json")
                ));

        mvc.perform(MockMvcRequestBuilders.get("/tasks/io?isAll=true")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", notNullValue()))
                .andExpect(jsonPath("$[0].description", notNullValue()))
                .andExpect(jsonPath("$[0].taskStatus", is("CREATED")))
                .andExpect(jsonPath("$[1].id", notNullValue()))
                .andExpect(jsonPath("$[1].description", notNullValue()))
                .andExpect(jsonPath("$[1].taskStatus", is("COMPLETED")));
    }

    @Test
    public void successFalseTest() throws Exception {
        stubFor(WireMock.get("/customer/tasks?isAll=false")
                .willReturn(ok()
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("getTasksIsAllFalse.json")
                ));

        mvc.perform(MockMvcRequestBuilders.get("/tasks/io?isAll=false")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", notNullValue()))
                .andExpect(jsonPath("$[0].description", notNullValue()))
                .andExpect(jsonPath("$[0].taskStatus", is("CREATED")));
    }

    @Test
    public void successWithoutFlagTest() throws Exception {
        stubFor(WireMock.get("/customer/tasks?isAll=false")
                .willReturn(ok()
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("getTasksIsAllFalse.json")
                ));

        mvc.perform(MockMvcRequestBuilders.get("/tasks/io")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", notNullValue()))
                .andExpect(jsonPath("$[0].description", notNullValue()))
                .andExpect(jsonPath("$[0].taskStatus", is("CREATED")));
    }
}
