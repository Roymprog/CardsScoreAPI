package nl.roymprog.cardsscore.models.requests;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ChineesPoepenRequest {
    private List<Score> scores;

    @Data
    public class Score {
        private final String player;
        private final List<Round> rounds;
        int round;
    }

    @Data
    public class Round {
        int pointsCalled;
        int pointsScored;
    }

//    public int getTotal() {
//        if (this.scores == null) {
//            return 0;
//        }
//
//        return this.scores.stream()
//                .flatMapToInt(score -> IntStream.of(score.pointsCalled))
//                .sum();
//    }
}
