package nl.roymprog.cardsscore.mocks;

import nl.roymprog.cardsscore.database.mongo.models.ChineesPoepenEntity;
import nl.roymprog.cardsscore.database.mongo.models.ChineesPoepenRoundScore;
import nl.roymprog.cardsscore.database.mongo.models.ChineesPoepenScore;
import nl.roymprog.cardsscore.models.ChineesPoepen;

import java.util.*;
import java.util.stream.Collectors;

public class MockFactory {
  private static final String gameId = "id";

  public static ChineesPoepen newChineesPoepen() {
    ChineesPoepen.Score roundScore = new ChineesPoepen.Score(6, 1, 1);
    List<ChineesPoepen.Score> score = new ArrayList<>();
    score.add(roundScore);

    Map<String, List<ChineesPoepen.Score>> scores = PlayersObjectFactory.getPlayers().stream()
            .collect(Collectors.toMap(x -> x, x -> score));

    return ChineesPoepen
            .builder()
              .host(PlayersObjectFactory.PLAYER_1)
              .round(1)
              .players(PlayersObjectFactory.getPlayers())
              .scores(scores)
              .build();
  }

  public static ChineesPoepen newChineesPoepenInvalidScoresCalled() {
    return ChineesPoepen
            .builder()
            .host(PlayersObjectFactory.PLAYER_1)
            .round(1)
            .players(PlayersObjectFactory.getPlayers())
            .scores(ScoresObjectFactory.getInvalidCalledScore())
            .build();
  }

  public static ChineesPoepen newChineesPoepenInvalidScoresScored() {
    return ChineesPoepen
            .builder()
            .host(PlayersObjectFactory.PLAYER_1)
            .round(1)
            .players(PlayersObjectFactory.getPlayers())
            .scores(ScoresObjectFactory.getInvalidScoredScore())
            .build();
  }

  public static ChineesPoepenEntity newChineesPoepenEntity() {
    Map<Integer, ChineesPoepenRoundScore> score = new HashMap<>();
    ChineesPoepenScore rounds = new ChineesPoepenScore(score);
    Map<String, ChineesPoepenScore> scores = new HashMap<>();

    scores.put(PlayersObjectFactory.PLAYER_1, rounds);
    scores.put(PlayersObjectFactory.PLAYER_2, rounds);
    scores.put(PlayersObjectFactory.PLAYER_3, rounds);
    scores.put(PlayersObjectFactory.PLAYER_4, rounds);

    return ChineesPoepenEntity
            .builder()
            .id(gameId)
            .players(PlayersObjectFactory.getPlayers())
            .host(PlayersObjectFactory.PLAYER_1)
            .round(1)
            .scores(scores)
            .build();
  }

}
