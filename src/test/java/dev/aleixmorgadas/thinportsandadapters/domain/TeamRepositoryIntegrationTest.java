package dev.aleixmorgadas.thinportsandadapters.domain;

import dev.aleixmorgadas.thinportsandadapters.AbstractIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class TeamRepositoryIntegrationTest extends AbstractIntegrationTest {
    @Autowired
    private TeamRepository teamRepository;

    @Test
    void createTeam() {
        var id = UUID.randomUUID();
        var team = new Team(id,"Benefits Team");
        teamRepository.save(team);

        var foundTeam = teamRepository.findById(id);
        assertThat(foundTeam).isPresent();
        assertThat(foundTeam.get().name()).isEqualTo("Benefits Team");
    }
}
