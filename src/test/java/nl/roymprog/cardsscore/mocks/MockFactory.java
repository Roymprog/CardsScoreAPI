package nl.roymprog.cardsscore.mocks;

import nl.roymprog.cardsscore.database.mongo.models.ChineesPoepenEntity;
import nl.roymprog.cardsscore.database.mongo.models.ChineesPoepenRoundScore;
import nl.roymprog.cardsscore.database.mongo.models.ChineesPoepenScore;
import nl.roymprog.cardsscore.models.ChineesPoepen;

import java.util.*;
import java.util.stream.Collectors;

public class MockFactory {
  private static final String host1 = "host1";
  private static final String host = "host";
  private static final String host2 = "host2";
  private static final String host3 = "host3";
  private static final String gameId = "id";

  public static ChineesPoepen newChineesPoepen() {
    ChineesPoepen.Score roundScore = new ChineesPoepen.Score(6, 1, 1);
    List<ChineesPoepen.Score> score = new ArrayList<>();
    score.add(roundScore);

    Map<String, List<ChineesPoepen.Score>> scores = PlayersObjectFactory.getPlayers().stream()
            .collect(Collectors.toMap(x -> x, x -> score));

    return ChineesPoepen
            .builder()
              .host(host)
              .round(1)
              .players(PlayersObjectFactory.getPlayers())
              .scores(scores)
              .build();
  }

  public static ChineesPoepen newChineesPoepenInvalidScoresCalled() {
    return ChineesPoepen
            .builder()
            .host(host)
            .round(1)
            .players(PlayersObjectFactory.getPlayers())
            .scores(ScoresObjectFactory.getInvalidCalledScore())
            .build();
  }

  public static ChineesPoepen newChineesPoepenInvalidScoresScored() {
    return ChineesPoepen
            .builder()
            .host(host)
            .round(1)
            .players(PlayersObjectFactory.getPlayers())
            .scores(ScoresObjectFactory.getInvalidScoredScore())
            .build();
  }

  public static ChineesPoepenEntity newChineesPoepenEntity() {
    Map<Integer, ChineesPoepenRoundScore> scores = new HashMap<>();
    ChineesPoepenScore score = new ChineesPoepenScore(scores);
    Map<String, ChineesPoepenScore> players = new HashMap<>();

    players.put(host, score);
    players.put(host1, score);
    players.put(host2, score);
    players.put(host3, score);

    return ChineesPoepenEntity
            .builder()
            .id(gameId)
            .host(host)
            .round(1)
            .players(players)
            .build();
  }

}
