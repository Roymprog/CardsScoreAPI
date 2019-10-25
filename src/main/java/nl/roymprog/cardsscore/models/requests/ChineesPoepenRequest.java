package nl.roymprog.cardsscore.models.requests;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.IntStream;

@Data
@NoArgsConstructor
public class ChineesPoepenRequest {
    private int round;
    private List<Score> scores;

    @Data
    public static class Score {
        String player;
        int pointsCalled;
        int pointsScored;
    }

    public int getTotal() {
        if (this.scores == null) {
            return 0;
        }

        return this.scores.stream()
                .flatMapToInt(score -> IntStream.of(score.pointsCalled))
                .sum();
    }
}
