package dev.aleixmorgadas.nothex.domain;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TeamRepository extends ListCrudRepository<Team, UUID> {
}
