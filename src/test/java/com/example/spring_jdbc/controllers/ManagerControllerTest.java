package com.example.spring_jdbc.controllers;

import com.example.spring_jdbc.dto.ManagerDto;
import com.example.spring_jdbc.model.Project;
import com.example.spring_jdbc.repository.ManagerRepository;
import com.example.spring_jdbc.service.ManagerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
class ManagerControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ManagerService managerService;

    @Autowired
    private ManagerRepository managerRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldSaveManager() throws Exception {
        ManagerDto managerDto = new ManagerDto(
                "Airat", 27, Project.TS, "Google");
        mockMvc.perform(post("/api/manager")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(managerDto)))
                .andExpect(status().isAccepted());
    }
}