package dev.aleixmorgadas.thinportsandadapters.web;

import com.jayway.jsonpath.JsonPath;
import dev.aleixmorgadas.thinportsandadapters.AbstractIntegrationTest;
import dev.aleixmorgadas.thinportsandadapters.domain.TeamRepository;
import dev.aleixmorgadas.thinportsandadapters.domain.TeamService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TeamControllerE2ETest extends AbstractIntegrationTest {
    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private TeamService teamService;

    private UUID teamId;

    @AfterAll
    void tearDown() {
        teamRepository.deleteAll();
    }

    @Test
    @Order(1)
    void createTeam() throws Exception {
        var response = mockMvc.perform(post("/teams")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\": \"Benefits Team\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Benefits Team"))
                .andReturn();

        // Extract the teamId from the response for later use
        teamId = UUID.fromString(JsonPath.read(response.getResponse().getContentAsString(), "$.id"));
    }

    @Test
    @Order(2)
    void seeAllTeams() throws Exception {
        mockMvc.perform(get("/teams")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Benefits Team"));
    }

    @Test
    @Order(3)
    void renameTeam() throws Exception {
        mockMvc.perform(patch("/teams/" + teamId + "/rename")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\": \"New Benefits Team\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("New Benefits Team"));

        assertThat(teamService.seeAllTeams().get(0).name()).isEqualTo("New Benefits Team");
    }
}