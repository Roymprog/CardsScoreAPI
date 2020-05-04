package nl.roymprog.cardsscore.database.mongo.models;

import com.google.common.collect.Maps;
import nl.roymprog.cardsscore.models.ChineesPoepen;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.Map.Entry;

public class ChineesPoepenConverter {
  public static ChineesPoepen toDto(ChineesPoepenEntity entity) {
    return ChineesPoepen.builder()
            .id(entity.id)
            .round(entity.round)
            .host(entity.host)
            .players(entity.players.keySet())
            .scores(entity.players
                    .entrySet()
                    .stream()
                    .collect(
                            Collectors.toMap(Entry::getKey, e -> toScoreList(e.getValue()))
                    )
            ).build();
  }

  public static ChineesPoepenEntity toEntity(ChineesPoepen cp) {
    return ChineesPoepenEntity.builder()
            .id(cp.getId())
            .host(cp.getHost())
            .round(cp.getRound())
            .players(Maps.transformValues(cp.getScores(), ChineesPoepenConverter::convertEntityRounds))
            .build();
  }

  private static ChineesPoepenScore convertEntityRounds(List<ChineesPoepen.Score> score) {
    Map<Integer, ChineesPoepen.Score> scoreMap = new HashMap<>();
    for (int i = 0; i < score.size(); i ++ ) {
      scoreMap.put(i, score.get(i));
    }
    return new ChineesPoepenScore(Maps.transformValues(scoreMap, ChineesPoepenConverter::convertRoundScores));
//    Map<Integer, ChineesPoepen.Score> scoreMap = Maps.uniqueIndex(score, ChineesPoepen.Score::getRound);
//    Map<Integer, ChineesPoepenRoundScore> cpScoreMap = Maps.transformValues(scoreMap, ChineesPoepenConverter::convertRoundScores);
//    return new ChineesPoepenScore(cpScoreMap);
  }

  private static List<ChineesPoepen.Score> toScoreList(ChineesPoepenScore cps) {
    return cps.getScore().values()
          .stream()
          .sorted()
          .map(ChineesPoepenConverter::convertRoundScores)
          .collect(Collectors.toList());
  }

  private static ChineesPoepen.Score convertRoundScores(ChineesPoepenRoundScore cps) {
    return new ChineesPoepen.Score(cps.getClaimed(), cps.getWon(), cps.getPoints());
  }

  private static ChineesPoepenRoundScore convertRoundScores(ChineesPoepen.Score score) {
    return new ChineesPoepenRoundScore(score.getScore(), score.getPointsCalled(), score.getPointsScored());
  }
}
