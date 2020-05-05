package nl.roymprog.cardsscore.database;

import nl.roymprog.cardsscore.models.ChineesPoepen;

import java.util.List;
import java.util.Optional;

public interface ChineesPoepenDbInterface {
  ChineesPoepen insertNew(ChineesPoepen chineesPoepen);

  Optional<ChineesPoepen> getGame(String gameId);

  ChineesPoepen updateGame(ChineesPoepen chineesPoepen);

  void deleteAll();

  List<ChineesPoepen> getGames(String userId);
}
