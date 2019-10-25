package nl.roymprog.cardsscore.models.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
@Builder
public class ChineesPoepenEntity {

    @Id
    private UUID id;

    private UUID host;

    private int round;

    @ElementCollection
    private Set<UUID> players = new HashSet<>();

    public Set<String> getPlayers() {
        return this.players.stream()
            .map(uuid -> uuid.toString())
            .collect(Collectors.toSet());
    }

    @ElementCollection
    private Map<UUID, GameScore> scores;

    public void addPlayer(UUID p) {
        this.players.add(p);
    }

    public void removePlayer(UUID p) {
        this.players.remove(p);
    }

    private LocalDateTime startTime;
    private LocalDateTime endTime;

    private String gameState;

    @Data
    @Entity
    class GameScore {
        @Id
        @GeneratedValue
        private UUID id;

        private int total;

        @ElementCollection
        List<RoundScore> roundRoundScores;
    }

    @Data
    @Entity
    class RoundScore {
        @Id
        @GeneratedValue
        private UUID id;

        int pointsCalled;
        int pointsScored;
    }
}
