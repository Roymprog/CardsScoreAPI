package nl.roymprog.cardsscore.database.mongo.models;

import lombok.Getter;
import org.springframework.data.annotation.PersistenceConstructor;

@Getter
public class ChineesPoepenRoundScore {
  private final int claimed;
  private State state;
  private int points;
  private int won;

  @PersistenceConstructor
  public ChineesPoepenRoundScore(int claimed) {
    this.claimed = claimed;
    this.state = State.STARTED;
  }

  @PersistenceConstructor
  public ChineesPoepenRoundScore(int claimed, int points, int won) {
    this.claimed = claimed;
    this.points = points;
    this.won = won;
    this.state = State.FINISHED;
  }

  enum State {
    STARTED,
    FINISHED;
  }
}
