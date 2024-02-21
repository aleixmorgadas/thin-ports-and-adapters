package dev.aleixmorgadas.nothex.web;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(TeamController.URI)
public class TeamController {
    public static final String URI = "/teams";

    @PostMapping
    public ResponseEntity<TeamResponse> createTeam(@RequestBody TeamRequest teamRequest) {
        return ResponseEntity.ok(new TeamResponse(teamRequest.name()));
    }

    @GetMapping
    public ResponseEntity<List<TeamResponse>> seeAllTeams() {
        return ResponseEntity.ok(List.of(new TeamResponse("Benefits Team")));
    }

    public record TeamRequest(String name) {
    }

    public record TeamResponse(String name) {
    }
}
