package nl.roymprog.cardsscore.database.mongo.models;

import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.Map;

@RequiredArgsConstructor
public class ChineesPoepenEntity {
  @Id
  public String id;

  public final String host;
  public final int round;
  public final Map<String, ChineesPoepenScore> players;
}
