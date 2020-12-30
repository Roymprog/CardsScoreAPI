package nl.roymprog.cardsscore.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ChineesPoepen {
  public static final int NUMBER_OF_ROUNDS = 17;
  public static final int NUMBER_OF_PLAYERS = 4;
  public static final String type = "CHINEES POEPEN";

  private String id;
  private String host;
  private Set<User> players;
  private int round;
  // @Todo: Create Object for scores
  private Map<String, List<Score>> scores;
  private LocalDateTime startTime;
  private String state;

  public Optional<Integer> getScore(String playerId) {
    List<Score> playerScores = this.scores.get(playerId);

    if (playerScores == null) {
      return Optional.empty();
    } else {
      return Optional.of(
              playerScores.stream()
                      .mapToInt(playerScore -> playerScore.getScore())
                      .sum());
    }
  }

  public List<Score> getScores(int round) {
    return scores.values().stream()
              .filter(list -> round <= list.size() )
              .map(list -> list.get(round - 1))
              .collect(Collectors.toList());
  }

  public void addScores(Map<String, Score> score , int round) {
    if (round == this.getRound()) {
      score.entrySet().stream()
        .forEach(e -> scores.get(e.getKey()).add(e.getValue()));
    } else if (round < this.getRound()) {
      score.entrySet().stream()
              .forEach(e -> scores.get(e.getKey()).set(round - 1, e.getValue()));
    } else {
      throw new IllegalArgumentException(
              String.format("Set rounds in order, round %d is not allowed, current round is %d", round, getRound())
      );
    }

    this.round = round + 1;
  }

  @Getter
  public static class Score {
    public Score(int pointsCalled, int pointsScored) {
      this.pointsCalled = pointsCalled;
      this.pointsScored = pointsScored;
      this.score = 0;
    }

    public Score(int pointsCalled, int pointsScored, int score) {
      this.pointsCalled = pointsCalled;
      this.pointsScored = pointsScored;
      this.score = score;
    }

    private final int pointsCalled;
    private final int pointsScored;
    private final int score;
  }
}
