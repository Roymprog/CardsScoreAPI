package nl.roymprog.cardsscore.database.mongo.models;

import com.google.common.collect.Maps;
import nl.roymprog.cardsscore.models.ChineesPoepen;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.Map.Entry;

public class ChineesPoepenConverter {
  public static ChineesPoepen toDto(ChineesPoepenEntity entity) {
    return ChineesPoepen.builder()
            .id(entity.id)
            .round(entity.round)
            .host(entity.host)
            .players(entity.players)
            .startTime(entity.startTime)
            .scores(entity.scores
                    .entrySet()
                    .stream()
                    .collect(
                            Collectors.toMap(Entry::getKey, e -> toScoreList(e.getValue()))
                    )

            ).build();
  }

  public static ChineesPoepenEntity toEntity(ChineesPoepen cp) {
    LocalDateTime localDateTime = cp.getStartTime() != null ? cp.getStartTime() : LocalDateTime.now();

    return ChineesPoepenEntity.builder()
            .id(cp.getId())
            .host(cp.getHost())
            .players(cp.getPlayers())
            .round(cp.getRound())
            .scores(Maps.transformValues(cp.getScores(), ChineesPoepenConverter::convertEntityRounds))
            .startTime(localDateTime)
            .build();
  }

  private static ChineesPoepenScore convertEntityRounds(List<ChineesPoepen.Score> score) {
    Map<Integer, ChineesPoepen.Score> scoreMap = new HashMap<>();
    for (int i = 0; i < score.size(); i ++ ) {
      scoreMap.put(i, score.get(i));
    }
    return new ChineesPoepenScore(Maps.transformValues(scoreMap, ChineesPoepenConverter::convertRoundScores));
  }

  private static List<ChineesPoepen.Score> toScoreList(ChineesPoepenScore cps) {
    return IntStream.range(0, cps.getScore().size())
            .mapToObj(i -> ChineesPoepenConverter.convertRoundScores(cps.getRoundScore(i)))
            .collect(Collectors.toList());
  }

  private static ChineesPoepen.Score convertRoundScores(ChineesPoepenRoundScore cps) {
    return new ChineesPoepen.Score(cps.getClaimed(), cps.getWon(), cps.getPoints());
  }

  private static ChineesPoepenRoundScore convertRoundScores(ChineesPoepen.Score score) {
    return new ChineesPoepenRoundScore(score.getScore(), score.getPointsCalled(), score.getPointsScored());
  }
}
