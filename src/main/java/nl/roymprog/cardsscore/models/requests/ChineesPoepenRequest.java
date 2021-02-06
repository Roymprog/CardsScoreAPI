package nl.roymprog.cardsscore.models.requests;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@Data
@NoArgsConstructor
public class ChineesPoepenRequest {
  private int round;
  private Map<String, Score> scores;

  @NoArgsConstructor
  @Data
  public static class Score {
    private int pointsCalled;
    private Integer pointsScored;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public Optional<Integer> getPointsScored() {
      return Optional.ofNullable(pointsScored);
    }
  }

  @JsonIgnore
  public Set<String> getPlayers() {
    return getScores().keySet();
  }
}
