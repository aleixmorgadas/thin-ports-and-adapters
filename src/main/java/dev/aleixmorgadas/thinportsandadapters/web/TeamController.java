package dev.aleixmorgadas.thinportsandadapters.web;

import dev.aleixmorgadas.thinportsandadapters.domain.TeamData;
import dev.aleixmorgadas.thinportsandadapters.domain.TeamService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(TeamController.URI)
@AllArgsConstructor
public class TeamController {
    public static final String URI = "/teams";
    private final TeamService teamService;

    @PostMapping
    public ResponseEntity<TeamData> createTeam(@RequestBody TeamRequest teamRequest) {
        var team = teamService.createTeam(teamRequest.name());
        return ResponseEntity.ok(team);
    }

    @GetMapping
    public ResponseEntity<List<TeamData>> seeAllTeams() {
        return ResponseEntity.ok(teamService.seeAllTeams());
    }

    @PatchMapping("/{id}/rename")
    public ResponseEntity<TeamData> renameTeam(
            @PathVariable String id,
            @RequestBody TeamRequest teamRequest) {
        var team = teamService.renameTeam(id, teamRequest.name());
        return ResponseEntity.ok(team);
    }

    public record TeamRequest(String name) {
    }
}
