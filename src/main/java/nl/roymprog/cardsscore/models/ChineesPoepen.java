package nl.roymprog.cardsscore.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
public class ChineesPoepen implements Game {
    public static final int NUMBER_OF_ROUNDS = 17;
    public static final int NUMBER_OF_PLAYERS = 4;
    public static final String type = "CHINEES POEPEN";

    private UUID id;
    private UUID host;
    private Set<UUID> players;

    @Override
    public void play() {

    }
}
