package nl.roymprog.cardsscore.models.response;

import lombok.Data;
import nl.roymprog.cardsscore.models.State;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Data
public class ChineesPoepenResponse {
  private final String id;
  private final String hostId;
  Set<UUID> players;
  private LocalDateTime startTime;
  LocalDateTime endTime;
  int round;

  public ChineesPoepenResponse(String id, String hostId, Set<UUID> players, int round) {
    this.id = id;
    this.hostId = hostId;
    this.startTime = LocalDateTime.now();
    this.players = players;
    this.round = round;
  }

  public ChineesPoepenResponse(String id, String hostId) {
    this.id = id;
    this.hostId = hostId;
  }
}