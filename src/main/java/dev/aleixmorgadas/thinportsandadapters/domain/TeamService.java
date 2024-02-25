package dev.aleixmorgadas.thinportsandadapters.domain;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
@Transactional
public class TeamService {
    private final TeamRepository teamRepository;

    public TeamData createTeam(String name) {
        Team team = teamRepository.save(new Team(UUID.randomUUID(), name));
        return new TeamData(team.id().toString(), team.name());
    }

    public List<TeamData> seeAllTeams() {
        return teamRepository.findAll()
                .stream()
                .map(team -> new TeamData(team.id().toString(), team.name()))
                .toList();
    }

    public TeamData renameTeam(String id, String name) {
        var team = teamRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new IllegalArgumentException("Team not found"));
        team.rename(name);
        return new TeamData(team.id().toString(), team.name());
    }

    public TeamData getTeam(String id) {
        var team = teamRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new IllegalArgumentException("Team not found"));
        return new TeamData(team.id().toString(), team.name());
    }
}
