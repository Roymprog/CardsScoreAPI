package nl.roymprog.cardsscore.mocks;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class PlayersObjectFactory {
  public static String PLAYER_1 = "18adb080-acae-42e8-9984-c9bf97259301";
  public static String PLAYER_2 = "18adb080-acae-42e8-9984-c9bf97259306";
  public static String PLAYER_3 = "18adb080-acae-42e8-9984-c9bf97259307";
  public static String PLAYER_4 = "18adb080-acae-42e8-9984-c9bf97259309";

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
    players.add(PLAYER_3);

    return players;
  }
}
