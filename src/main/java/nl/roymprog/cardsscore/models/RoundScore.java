package nl.roymprog.cardsscore.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import nl.roymprog.cardsscore.services.ChineesPoepen;

import java.util.UUID;

@Data
@AllArgsConstructor
public class RoundScore {
    private UUID playerId;
    private int pointsCalled;
    private int pointsScored;
    private int score;
    private ChineesPoepen.Round round;
}
