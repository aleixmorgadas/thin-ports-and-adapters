package dev.aleixmorgadas.thinportsandadapters.domain;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TeamServiceTest {
    private TeamRepository teamRepository;

    @BeforeAll
    void setup() {
        teamRepository = mock(TeamRepository.class);
    }

    @Test
    void createTeam() {
        var id = UUID.randomUUID();
        when(teamRepository.save(any())).thenReturn(new Team(id, "Benefits Team"));

        TeamService teamService = new TeamService(teamRepository);
        TeamData teamData = teamService.createTeam("Benefits Team");
        assertEquals("Benefits Team", teamData.name());
    }
}