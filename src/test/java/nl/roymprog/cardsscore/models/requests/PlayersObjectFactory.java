package nl.roymprog.cardsscore.models.requests;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class PlayersObjectFactory {
    public static String PLAYER_1 = UUID.randomUUID().toString();
    public static String PLAYER_2 = UUID.randomUUID().toString();
    public static String PLAYER_3 = UUID.randomUUID().toString();
    public static String PLAYER_4 = UUID.randomUUID().toString();

    public static Set<String> getPlayers() {
        Set<String> players = new HashSet<>();
        players.add(PLAYER_1);
        players.add(PLAYER_2);
        players.add(PLAYER_3);
        players.add(PLAYER_4);

        return players;
    }

    public static Set<String> getInvalidPlayers() {
        Set<String> players = new HashSet<>();
        players.add(PLAYER_1);
        players.add(PLAYER_2);
        players.add("PLAYER_3");

        return players;
    }
}
