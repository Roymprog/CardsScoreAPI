package nl.roymprog.cardsscore.database.mongo.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class ChineesPoepenRoundScore {
  private final int points;
  private final int claimed;
  private final int won;
}
