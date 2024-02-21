package dev.aleixmorgadas.nothex.web;

import dev.aleixmorgadas.nothex.AbstractIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class TeamControllerTest extends AbstractIntegrationTest {

    @Test
    void createTeam() throws Exception {
        mockMvc.perform(post("/teams")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\": \"Benefits Team\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Benefits Team"));
    }
}