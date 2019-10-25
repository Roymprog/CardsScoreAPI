package nl.roymprog.cardsscore.models;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
@Builder
public class ChineesPoepen {
    public static final int NUMBER_OF_ROUNDS = 17;
    public static final int NUMBER_OF_PLAYERS = 4;
    public static final String type = "CHINEES POEPEN";

    private String id;
    private String host;
    private Set<String> players;
    private int round;
    private Map<String, List<Score>> scores;
    private LocalDateTime startTime;
    private String state;

    public Set<UUID> getPlayers() {
        return this.players.stream()
                .map(id -> UUID.fromString(id))
                .collect(Collectors.toSet());
    }

    public static class Score {
        int pointsCalled;
        int pointsScored;
    }
}
