package nl.roymprog.cardsscore.businessDelegate;

import com.google.common.collect.Maps;
import lombok.extern.java.Log;
import nl.roymprog.cardsscore.database.mongo.models.ChineesPoepenRoundScore;
import nl.roymprog.cardsscore.database.mongo.models.ChineesPoepenScore;
import nl.roymprog.cardsscore.models.ChineesPoepen;
import nl.roymprog.cardsscore.models.requests.ChineesPoepenCreateRequest;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static nl.roymprog.cardsscore.models.ChineesPoepen.NUMBER_OF_ROUNDS;

@Component
@Log
public class ChineesPoepenBusinessDelegateImpl implements ChineesPoepenBusinessDelegate {
  public ChineesPoepen createGame(ChineesPoepenCreateRequest dto) {
    Map<String, List<ChineesPoepen.Score>> scores = Maps.toMap(dto.getPlayers(),
              player -> new ArrayList<>()
            );

    ChineesPoepen cp = ChineesPoepen.builder()
            .host(dto.getHost())
            .players(dto.getPlayers())
            .round(1)
            .startTime(LocalDateTime.now())
            .state("CREATED")
            .scores(scores)
            .build();

    // TODO: allow for determining dealer in backend
    if (cp.getPlayers().size() < ChineesPoepen.NUMBER_OF_PLAYERS) {
      String message = "Cannot create game, number of players is not " + ChineesPoepen.NUMBER_OF_PLAYERS;
      log.severe(message);
      throw new IllegalArgumentException(message);
    }

    return cp;
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
    int sum = scores.stream()
            .mapToInt(score -> score.getPointsScored())
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
    int diff = Math.abs(score.getPointsCalled() - score.getPointsScored());
    int bonus = (round >= 6 && round <= 12 && score.getPointsCalled() == 0) ? 10 : 5;

    int points =  score.getPointsCalled() == score.getPointsScored() ? bonus + score.getPointsCalled() : -diff;

    return new ChineesPoepen.Score(score.getPointsCalled(), score.getPointsScored(), points);
  }

  public List<ChineesPoepen.Score> calculateScores(List<ChineesPoepen.Score> scores) {
    return IntStream.range(0, scores.size())
            .mapToObj(i -> calculateScore(scores.get(i), i + 1))
            .collect(Collectors.toList());
  }
}
