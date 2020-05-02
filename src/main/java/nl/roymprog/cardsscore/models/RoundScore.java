package nl.roymprog.cardsscore.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RoundScore {
  private String playerId;
  private int pointsCalled;
  private int pointsScored;
  private int score;
}
