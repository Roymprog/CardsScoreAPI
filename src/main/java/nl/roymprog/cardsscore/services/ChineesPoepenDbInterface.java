package nl.roymprog.cardsscore.services;

import nl.roymprog.cardsscore.models.ChineesPoepen;
import nl.roymprog.cardsscore.models.entity.ChineesPoepenEntity;

import java.util.Optional;

public interface ChineesPoepenDbInterface {
    ChineesPoepen insertNew(ChineesPoepen chineesPoepen);

    Optional<ChineesPoepenEntity> getGame(String gameId);

    ChineesPoepen updateGame(ChineesPoepen chineesPoepen);
}
