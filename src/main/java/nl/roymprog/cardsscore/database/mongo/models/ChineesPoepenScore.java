package nl.roymprog.cardsscore.database.mongo.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Map;

@RequiredArgsConstructor
@Getter
public class ChineesPoepenScore {
  private final Map<Integer, ChineesPoepenRoundScore> score;
}
