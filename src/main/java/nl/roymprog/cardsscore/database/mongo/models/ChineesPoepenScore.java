package nl.roymprog.cardsscore.database.mongo.models;

import lombok.RequiredArgsConstructor;

import java.util.Map;

@RequiredArgsConstructor
public class ChineesPoepenScore {
    private final Map<Integer, ChineesPoepenRoundScore> score;
}
