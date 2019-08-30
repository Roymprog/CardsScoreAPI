package nl.roymprog.cardsscore.models.entity;

import lombok.Data;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;
import java.util.UUID;

@Entity
@Data
public class ChineesPoepen extends Game {

    public static final int NUMBER_OF_ROUNDS = 17;

    @ElementCollection
    private List<GameScore> rounds;

    @Data
    @Entity
    class GameScore {
        @Id
        UUID id;
        private UUID playerId;
        private int score;
    }
}
