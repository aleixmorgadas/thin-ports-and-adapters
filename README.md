# thin-ports-and-adapters

This is an example of a Ports and Adapters (P&A for short) architecture in Java 21 and Spring Boot.

We can define a simple P&A architecture as follows:

```mermaid
graph LR
    RestControllerAdapter --> ApplicationService
    ApplicationService --> Entity
    Entity --> RepositoryPort
    RepositoryPort --> RepositorySQLAdapter
    subgraph Outside 
        RestControllerAdapter
        RepositorySQLAdapter
    end
    subgraph Inside 
        ApplicationService
        Entity
        RepositoryPort
    end
```

A possible Spring Boot implementation of the adapters is:

```java
// RestControllerAdapter
@RestController
class TeamController {
    private final TeamService teamService;
    
    @GetMapping("/teams")
    public List<TeamData> getTeams() {
        return teamService.getTeams();
    }
}

// TeamRepositoryPort
interface TeamRepository extends ListCrudRepository<Team, Long> {
}

// The SQL Adapter of the TeamRepositoryPort is
// provided by Spring Data JPA.
```

## Running the application

```shell
./gradlew bootTestRun
```

ℹ️ It uses [Spring Boot integration][sbit] with Testcontainers to spin up a PostgreSQL container.

## Running the tests

```shell
./gradlew test
```

[sbit]: https://spring.io/blog/2023/06/23/improved-testcontainers-support-in-spring-boot-3-1