package dev.aleixmorgadas.thinportsandadapters.domain;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class TeamService {
    private final TeamRepository teamRepository;

    public Team createTeam(String name) {
        return teamRepository.save(new Team(UUID.randomUUID(), name));
    }

    public List<Team> seeAllTeams() {
        return teamRepository.findAll();
    }

}
