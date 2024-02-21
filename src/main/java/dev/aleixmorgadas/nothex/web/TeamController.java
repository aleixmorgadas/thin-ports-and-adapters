package dev.aleixmorgadas.nothex.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(TeamController.URI)
public class TeamController {
    public static final String URI = "/teams";

    @PostMapping
    public ResponseEntity<TeamResponse> createTeam(@RequestBody TeamRequest teamRequest) {
        return ResponseEntity.ok(new TeamResponse(teamRequest.name()));
    }

    public record TeamRequest(String name) {
    }

    public record TeamResponse(String name) {
    }
}
