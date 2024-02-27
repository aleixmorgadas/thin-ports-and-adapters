package dev.aleixmorgadas.thinportsandadapters.domain;

import jakarta.persistence.*;
import lombok.ToString;

import java.util.UUID;


@Entity(name = "teams")
@ToString
class Team {
    @Id
    private UUID id;
    private String name;

    public Team(UUID id, String name) {
        this.id = id;
        this.name = name;
    }

    public Team() {
    }

    public UUID id() {
        return id;
    }

    public String name() {
        return name;
    }

    public void rename(String name) {
        this.name = name;
    }
}