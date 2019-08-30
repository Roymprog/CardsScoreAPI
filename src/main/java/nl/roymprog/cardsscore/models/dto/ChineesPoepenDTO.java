package nl.roymprog.cardsscore.models.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import nl.roymprog.cardsscore.configuration.chineespoepensm.ChineesPoepenEvents;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@Data
public class ChineesPoepenDTO {

    public static final String CHINEES_POEPEN_MESSAGE_HEADER = "CHINEES_POEPEN_DTO";
    public static final int NUMBER_OF_PLAYERS = 3;

    private UUID id;
    private ChineesPoepenEvents event;
    private UUID hostId;
    private List<UUID> players;
    private int round;
    private HashMap<UUID, Integer> predictedScore;
    private HashMap<UUID, Integer> actualScore;
}
