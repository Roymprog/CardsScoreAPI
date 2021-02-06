package nl.roymprog.cardsscore.database.mongo.models;

import com.google.common.collect.Maps;
import nl.roymprog.cardsscore.models.ChineesPoepen;
import nl.roymprog.cardsscore.models.User;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.Map.Entry;
import static nl.roymprog.cardsscore.database.mongo.models.ChineesPoepenRoundScore.State.FINISHED;

public class ChineesPoepenConverter {
  public static ChineesPoepen toDto(ChineesPoepenEntity entity) {
    Set<User> users =
            entity.players.stream()
                    .map((userEntity) -> new User(userEntity.id, userEntity.name))
                    .collect(Collectors.toSet());

    return ChineesPoepen.builder()
            .id(entity.id)
            .round(entity.round)
            .host(entity.host)
            .players(users)
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
    Set<UserEntity> userEntities =
            cp.getPlayers().stream()
                    .map(UserConverter::toEntity)
                    .collect(Collectors.toSet());

    return ChineesPoepenEntity.builder()
            .id(cp.getId())
            .host(cp.getHost())
            .players(userEntities)
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
    if (cps.getState() == FINISHED) {
      return new ChineesPoepen.Score(cps.getClaimed(), cps.getPoints(), cps.getWon());
    } else {
      return new ChineesPoepen.Score(cps.getClaimed());
    }
  }

  private static ChineesPoepenRoundScore convertRoundScores(ChineesPoepen.Score score) {
    if (score.getPointsScored().isEmpty()) {
      return new ChineesPoepenRoundScore(score.getPointsCalled());
    } else {
      return new ChineesPoepenRoundScore(score.getPointsCalled(), score.getPointsScored().get(), score.getScore().get());
    }
  }
}
