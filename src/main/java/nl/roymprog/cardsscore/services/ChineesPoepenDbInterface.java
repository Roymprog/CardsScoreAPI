package nl.roymprog.cardsscore.services;

import nl.roymprog.cardsscore.models.entity.ChineesPoepenEntity;

import java.util.Set;

public interface ChineesPoepenDbInterface {
    ChineesPoepenEntity createGame(String hostId, Set<String> players, int round);
}
