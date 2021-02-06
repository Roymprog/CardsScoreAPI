package nl.roymprog.cardsscore.businessDelegate;

import com.google.common.collect.Maps;
import lombok.extern.java.Log;
import nl.roymprog.cardsscore.models.ChineesPoepen;
import nl.roymprog.cardsscore.models.requests.ChineesPoepenCreateRequest;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static nl.roymprog.cardsscore.models.ChineesPoepen.NUMBER_OF_ROUNDS;

@Component
@Log
public class ChineesPoepenBusinessDelegateImpl implements ChineesPoepenBusinessDelegate {
  public ChineesPoepen createGame(ChineesPoepen cp) {
    if (cp.getHost() == null) {
      String message = "Host must be provided";
      log.severe(message);
      throw new IllegalArgumentException(message);
    }
    // TODO: allow for determining dealer in backend
    if (cp.getPlayers().size() < ChineesPoepen.NUMBER_OF_PLAYERS) {
      String message = "Cannot create game, number of players is not " + ChineesPoepen.NUMBER_OF_PLAYERS;
      log.severe(message);
      throw new IllegalArgumentException(message);
    }

    Set<String> playerNames = cp.getPlayers().stream().map(player -> player.getId()).collect(Collectors.toSet());

    Map<String, List<ChineesPoepen.Score>> scores = Maps.toMap(playerNames,
              player -> new ArrayList<>()
            );

    return ChineesPoepen.builder()
            .host(cp.getHost())
            .players(cp.getPlayers())
            .round(1)
            .startTime(LocalDateTime.now())
            .state("CREATED")
            .scores(scores)
            .build();
  }

  public void validateRound(List<ChineesPoepen.Score> scoreList, int round) {
    validateRoundNumber(round);

    if (!roundScoresCalledValid(scoreList, round)) {
      String errorMessage = String.format("Sum of called cannot equal hand size for round %d", round);
      throw new IllegalArgumentException(errorMessage);
    }

    if (!roundScoresScoredValid(scoreList, round)) {
      String errorMessage = String.format("Sum of scored points has to equal hand size for round %d", round);
      throw new IllegalArgumentException(errorMessage);
    }
  }

  private void validateRoundNumber(int round) {
    if (round < 1 || round > NUMBER_OF_ROUNDS) {
      String errorMessage = String.format("Round number should be greater than 1 and smaller or equal to %d", NUMBER_OF_ROUNDS);
      throw new IllegalArgumentException(errorMessage);
    }
  }

  private boolean roundScoresCalledValid(List<ChineesPoepen.Score> scores, int round) {
    int sum = scores.stream()
            .mapToInt(score -> score.getPointsCalled())
            .sum();

    return sum != getHandSizeForRound(round);
  }

  public boolean roundScoresScoredValid(List<ChineesPoepen.Score> scores, int round) {
    if (scores.stream().anyMatch(score -> score.getPointsScored().isEmpty())) {
      return true;
    }

    int sum = scores.stream()
            .mapToInt(score -> score.getPointsScored().orElse(0))
            .sum();

    return sum == getHandSizeForRound(round);
  }

  private int getHandSizeForRound(int round) {
    validateRoundNumber(round);

    if (round < 10) {
      return round;
    } else {
      return 8 - round % 10;
    }
  }

  public ChineesPoepen.Score calculateScore(ChineesPoepen.Score score, int round) {
    return score.getPointsScored().map(pointsScored -> {
      int pointsCalled = score.getPointsCalled();
      int diff = Math.abs(pointsCalled - pointsScored);
      int bonus = (round >= 6 && round <= 12 && score.getPointsCalled() == 0) ? 10 : 5;

      int points =  pointsCalled == pointsScored ? bonus + score.getPointsCalled() : -diff;

      return new ChineesPoepen.Score(score.getPointsCalled(), pointsScored, points);

    }).orElse(score);
  }

  public List<ChineesPoepen.Score> calculateScores(List<ChineesPoepen.Score> scores) {
    return IntStream.range(0, scores.size())
            .mapToObj(i -> calculateScore(scores.get(i), i + 1))
            .collect(Collectors.toList());
  }
}
