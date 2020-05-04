package nl.roymprog.cardsscore.database.mongo.models;

import nl.roymprog.cardsscore.models.ChineesPoepen;

import java.util.*;

public class MockFactory {
  private static final String host1 = "host1";
  private static final String host = "host";
  private static final String host2 = "host2";
  private static final String host3 = "host3";
  private static final String gameId = "id";


  public static ChineesPoepen newChineesPoepen() {
    ChineesPoepen.Score roundScore = new ChineesPoepen.Score(6, 1, 1);
    List<ChineesPoepen.Score> score = new ArrayList<>();
    Map<String, List<ChineesPoepen.Score>> scores = new HashMap<>();

    score.add(roundScore);
    scores.put(host, score);
    scores.put(host1, score);
    scores.put(host2, score);
    scores.put(host3, score);

    Set<String> players = new HashSet<>();

    players.add(host);
    players.add(host1);
    players.add(host2);
    players.add(host3);

    return ChineesPoepen
            .builder()
              .host(host)
              .round(1)
              .players(players)
              .scores(scores)
              .build();
  }

  public static ChineesPoepenEntity newChineesPoepenEntity() {
    ChineesPoepen.Score roundScore = new ChineesPoepen.Score(6, 1, 1);
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
