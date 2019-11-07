package nl.roymprog.cardsscore.businessDelegate;

import nl.roymprog.cardsscore.models.ChineesPoepen;
import nl.roymprog.cardsscore.models.requests.ChineesPoepenCreateRequest;

public interface ChineesPoepenBusinessDelegate {
    ChineesPoepen createGame(String host, ChineesPoepenCreateRequest dto);

    ChineesPoepen playRound(ChineesPoepen cp);
}
