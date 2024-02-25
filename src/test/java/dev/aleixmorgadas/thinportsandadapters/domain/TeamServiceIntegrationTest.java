package dev.aleixmorgadas.thinportsandadapters.domain;

import dev.aleixmorgadas.thinportsandadapters.AbstractIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

public class TeamServiceIntegrationTest extends AbstractIntegrationTest {
    @Autowired
    private TeamService teamService;

    @Test
    void createTeam() {
        TeamData teamData = teamService.createTeam("Benefits Team");
        assertThat("Benefits Team").isEqualTo(teamData.name());
    }
}
