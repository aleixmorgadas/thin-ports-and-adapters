package dev.aleixmorgadas.thinportsandadapters.domain;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class TeamService {
    private final TeamRepository teamRepository;

    public TeamData createTeam(String name) {
        Team team = teamRepository.save(new Team(UUID.randomUUID(), name));
        return new TeamData(team.name());
    }

    public List<TeamData> seeAllTeams() {
        return teamRepository.findAll()
                .stream()
                .map(team -> new TeamData(team.name()))
                .toList();
    }

}
