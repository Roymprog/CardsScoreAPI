package nl.roymprog.cardsscore.businessDelegate;

import nl.roymprog.cardsscore.models.ChineesPoepen;
import nl.roymprog.cardsscore.models.requests.ChineesPoepenCreateRequest;
import nl.roymprog.cardsscore.models.requests.ChineesPoepenRequest;

import java.util.List;

public interface ChineesPoepenBusinessDelegate {
  ChineesPoepen createGame(ChineesPoepenCreateRequest dto);

  void validateRound(List<ChineesPoepen.Score> score, int round);

  ChineesPoepen.Score calculateScore(ChineesPoepen.Score score, int round);

  List<ChineesPoepen.Score> calculateScores(List<ChineesPoepen.Score> score);
}
