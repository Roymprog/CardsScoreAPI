package nl.roymprog.cardsscore.models.requests;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

@Data
@NoArgsConstructor
public class ChineesPoepenRequest {
    private Set<String> players;
    private List<Score> scores;

    @Data
    public static class Score {
        String player;
        int score;
    }

    public int getTotal() {
        if (this.scores == null) {
            return 0;
        }

        return this.scores.stream()
                .flatMapToInt(score -> IntStream.of(score.score))
                .sum();
    }
}
