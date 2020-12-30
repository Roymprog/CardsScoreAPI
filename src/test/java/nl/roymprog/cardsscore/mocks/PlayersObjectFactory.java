package nl.roymprog.cardsscore.mocks;

import nl.roymprog.cardsscore.models.User;

import java.util.HashSet;
import java.util.Set;

public class PlayersObjectFactory {
  public static User PLAYER_1 = new User("18adb080-acae-42e8-9984-c9bf97259301", "18adb080-acae-42e8-9984-c9bf97259301");
  public static User PLAYER_2 = new User("18adb080-acae-42e8-9984-c9bf97259306", "18adb080-acae-42e8-9984-c9bf97259306");
  public static User PLAYER_3 = new User("18adb080-acae-42e8-9984-c9bf97259307", "18adb080-acae-42e8-9984-c9bf97259307");
  public static User PLAYER_4 = new User("18adb080-acae-42e8-9984-c9bf97259309", "18adb080-acae-42e8-9984-c9bf97259309");

  public static Set<User> getPlayers() {
    Set<User> players = new HashSet<>();
    players.add(PLAYER_1);
    players.add(PLAYER_2);
    players.add(PLAYER_3);
    players.add(PLAYER_4);

    return players;
  }

  public static Set<User> getInvalidPlayers() {
    Set<User> players = new HashSet<>();
    players.add(PLAYER_1);
    players.add(PLAYER_2);
    players.add(PLAYER_3);

    return players;
  }
}
