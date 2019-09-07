package nl.roymprog.cardsscore.models.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

@Data
@NoArgsConstructor
@Entity
public class ChineesPoepenEntity {

    @Id
    @GeneratedValue
    private UUID id;

    private UUID host;

    private int round;

    @ElementCollection
    private Set<UUID> players = new HashSet<>();

    public void addPlayer(UUID p) {
        this.players.add(p);
    }

    public void removePlayer(UUID p) {
        this.players.remove(p);
    }

    private LocalDateTime startTime;
    private LocalDateTime endTime;

    private String gameState;

//    @ElementCollection
//    private List<GameScore> rounds = new ArrayList<>();
//
//    @Data
//    @Entity
//    class GameScore {
//        @Id
//        java.util.UUID id;
//        private java.util.UUID playerId;
//        private int score;
//    }
}
