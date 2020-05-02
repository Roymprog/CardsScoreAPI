package nl.roymprog.cardsscore.database.mongo.models;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ChineesPoepenRoundScore {
  private final int points;
  private final int claimed;
  private final int won;
}
