package dev.aleixmorgadas.thinportsandadapters.web;


import dev.aleixmorgadas.thinportsandadapters.domain.TeamData;
import dev.aleixmorgadas.thinportsandadapters.domain.TeamService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.http.HttpStatus;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TeamControllerMockingServiceTest {
    private TeamController teamController;
    private TeamService teamService;

    @BeforeAll
    public void setUp() {
        teamService = mock(TeamService.class);
        teamController = new TeamController(teamService);
    }

    @Test
    public void createTeam() {
        when(teamService.createTeam("Benefits Team"))
                .thenReturn(new TeamData(UUID.randomUUID().toString(), "Benefits Team"));

        var response = teamController.createTeam(new TeamController.TeamRequest("Benefits Team"));
        assertNotNull(response);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().name()).isEqualTo("Benefits Team");
    }
}
