package dev.aleixmorgadas.nothex.web;

import dev.aleixmorgadas.nothex.AbstractIntegrationTest;
import dev.aleixmorgadas.nothex.domain.TeamRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TeamControllerTest extends AbstractIntegrationTest {
    @Autowired
    private TeamRepository teamRepository;

    @AfterAll
    void tearDown() {
        teamRepository.deleteAll();
    }

    @Test
    @Order(1)
    void createTeam() throws Exception {
        mockMvc.perform(post("/teams")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\": \"Benefits Team\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Benefits Team"));
    }

    @Test
    @Order(2)
    void seeAllTeams() throws Exception {
        mockMvc.perform(get("/teams")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Benefits Team"));
    }
}