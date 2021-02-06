package nl.roymprog.cardsscore.mocks;

import nl.roymprog.cardsscore.models.ChineesPoepen;

import java.time.LocalDateTime;
import java.util.UUID;

public class ChineesPoepenObjectFactory {

  public static String HOST_ID = UUID.randomUUID().toString();
  public static String CHINEESPOEPEN_ID = UUID.randomUUID().toString();

  public static ChineesPoepen getChineesPoepen() {
    return ChineesPoepen.builder()
            .host(HOST_ID)
            .startTime(LocalDateTime.now())
            .players(PlayersObjectFactory.getPlayers())
            .round(1)
            .id(CHINEESPOEPEN_ID)
            .scores(ScoresObjectFactory.getRoundScores())
            .build();
  }

  public static ChineesPoepen getChineesPoepenPointsCalledOnly() {
    return ChineesPoepen.builder()
            .host(HOST_ID)
            .startTime(LocalDateTime.now())
            .players(PlayersObjectFactory.getPlayers())
            .round(1)
            .id(CHINEESPOEPEN_ID)
            .scores(ScoresObjectFactory.getRoundScoresCalledOnly())
            .build();
  }

  public static ChineesPoepen getChineesPoepenFullGame() {
    return ChineesPoepen.builder()
            .host(HOST_ID)
            .startTime(LocalDateTime.now())
            .players(PlayersObjectFactory.getPlayers())
            .round(1)
            .id(CHINEESPOEPEN_ID)
            .scores(ScoresObjectFactory.getFullGame())
            .build();
  }
}
