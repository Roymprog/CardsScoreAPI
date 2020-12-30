package nl.roymprog.cardsscore.database.mongo.models;

import lombok.Builder;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Set;

@Builder
public class ChineesPoepenEntity {
  @Id
  public String id;

  public final String host;
  public final int round;
  public final Set<UserEntity> players;
  public final Map<String, ChineesPoepenScore> scores;
  public final LocalDateTime startTime;
}
