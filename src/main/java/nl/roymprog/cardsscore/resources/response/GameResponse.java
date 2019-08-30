package nl.roymprog.cardsscore.resources.response;

import lombok.Data;
import nl.roymprog.cardsscore.configuration.chineespoepensm.ChineesPoepenStates;

import java.util.List;
import java.util.UUID;

@Data
public class GameResponse {
    private UUID id;
    private ChineesPoepenStates state;
    private UUID host;
    private List<UUID> players;
}
