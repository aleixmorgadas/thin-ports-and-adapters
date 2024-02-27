package dev.aleixmorgadas.thinportsandadapters.web;

import com.jayway.jsonpath.JsonPath;
import dev.aleixmorgadas.thinportsandadapters.AbstractIntegrationTest;
import dev.aleixmorgadas.thinportsandadapters.domain.TeamData;
import dev.aleixmorgadas.thinportsandadapters.domain.TeamRepository;
import dev.aleixmorgadas.thinportsandadapters.domain.TeamService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TeamControllerIntegrationTest extends AbstractIntegrationTest {
    @MockBean
    private TeamService teamService;

    private UUID teamId;

    @Test
    @Order(1)
    void createTeam() throws Exception {
        when(teamService.createTeam("Benefits Team"))
                .thenReturn(new TeamData(UUID.randomUUID().toString(), "Benefits Team"));

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
        when(teamService.seeAllTeams())
                .thenReturn(List.of(new TeamData(teamId.toString(), "Benefits Team")));

        mockMvc.perform(get("/teams")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Benefits Team"));
    }

    @Test
    @Order(3)
    void renameTeam() throws Exception {
        when(teamService.renameTeam(teamId.toString(), "New Benefits Team"))
                .thenReturn(new TeamData(teamId.toString(), "New Benefits Team"));

        mockMvc.perform(patch("/teams/" + teamId + "/rename")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\": \"New Benefits Team\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("New Benefits Team"));
    }
}