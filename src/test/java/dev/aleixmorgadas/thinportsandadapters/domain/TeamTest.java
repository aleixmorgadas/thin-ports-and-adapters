package dev.aleixmorgadas.thinportsandadapters.domain;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class TeamTest {

    @Test
    void createTeam() {
        Team team = new Team(UUID.randomUUID(), "Benefits Team");
        assertEquals("Benefits Team", team.name());
    }

    @Test
    void renameTeam() {
        Team team = new Team(UUID.randomUUID(), "Benefits Team");
        team.rename("New Benefits Team");
        assertEquals("New Benefits Team", team.name());
    }
}