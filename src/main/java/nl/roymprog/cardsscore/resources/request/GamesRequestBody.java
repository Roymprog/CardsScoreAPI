package nl.roymprog.cardsscore.resources.request;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class GamesRequestBody {
    private String gameType;
    private List<UUID> playerIds;
}
