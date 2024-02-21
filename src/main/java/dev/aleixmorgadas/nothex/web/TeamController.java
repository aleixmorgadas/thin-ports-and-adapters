package dev.aleixmorgadas.nothex.web;

import dev.aleixmorgadas.nothex.domain.Team;
import dev.aleixmorgadas.nothex.domain.TeamService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(TeamController.URI)
@AllArgsConstructor
public class TeamController {
    public static final String URI = "/teams";
    private final TeamService teamService;

    @PostMapping
    public ResponseEntity<TeamResponse> createTeam(@RequestBody TeamRequest teamRequest) {
        var team = teamService.createTeam(new Team(UUID.randomUUID(), teamRequest.name()));
        return ResponseEntity.ok(new TeamResponse(team.name()));
    }

    @GetMapping
    public ResponseEntity<List<TeamResponse>> seeAllTeams() {
        return ResponseEntity.ok(teamService.seeAllTeams().stream()
                .map(team -> new TeamResponse(team.name()))
                .toList());
    }

    public record TeamRequest(String name) {
    }

    public record TeamResponse(String name) {
    }
}
