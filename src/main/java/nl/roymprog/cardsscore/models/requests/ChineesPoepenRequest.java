package nl.roymprog.cardsscore.models.requests;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Data
@NoArgsConstructor
public class ChineesPoepenRequest {
  private String host;
  private List<Score> scores;

  @NoArgsConstructor
  @Data
  public static class Score {
    private String player;
    private List<Round> rounds;
  }

  @NoArgsConstructor
  @Data
  public static class Round {
    private int round;
    private int pointsCalled;
    private int pointsScored;
  }

  public Optional<Score> getScores(String player) {
    return this.scores.stream()
            .filter(score -> score.getPlayer().equals(player))
            .findFirst();
  }

  public Set<String> getPlayers() {
    Set<String> players = new HashSet<>();

    this.scores.stream()
            .forEach(score -> players.add(score.getPlayer()));

    return players;
  }
}
