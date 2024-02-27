package dev.aleixmorgadas.thinportsandadapters.domain;

import dev.aleixmorgadas.thinportsandadapters.AbstractIntegrationTest;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TeamRepositoryIntegrationTest extends AbstractIntegrationTest {
    @Autowired
    private TeamRepository teamRepository;

    @AfterAll
    void tearDown() {
        teamRepository.deleteAll();
    }

    @Test
    void saveTeam() {
        var id = UUID.randomUUID();
        var team = new Team(id,"Benefits Team");
        teamRepository.save(team);

        var foundTeam = teamRepository.findById(id);
        assertThat(foundTeam).isPresent();
        assertThat(foundTeam.get().name()).isEqualTo("Benefits Team");
    }
}
