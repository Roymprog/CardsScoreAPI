package nl.roymprog.cardsscore.models.entity;

import nl.roymprog.cardsscore.models.ChineesPoepen;

import java.util.UUID;

public class ChineesPoepenConverter {
    public static ChineesPoepen toDto(ChineesPoepenEntity entity) {
        return ChineesPoepen.builder()
                .host(entity.getHost().toString())
                .players(entity.getPlayers())
                .round(entity.getRound())
                .state(entity.getGameState())
                .startTime(entity.getStartTime())
                .id(entity.getId().toString())
                .build();
    }

    public static ChineesPoepenEntity toEntity(ChineesPoepen cp) {
        return ChineesPoepenEntity.builder()
                .host(UUID.fromString(cp.getHost()))
                .players(cp.getPlayers())
                .round(cp.getRound())
                .gameState(cp.getState())
                .startTime(cp.getStartTime())
                .id(UUID.fromString(cp.getId()))
                .build();
    }
}
