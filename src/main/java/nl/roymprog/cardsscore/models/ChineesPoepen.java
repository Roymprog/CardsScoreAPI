package nl.roymprog.cardsscore.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import nl.roymprog.cardsscore.models.requests.ChineesPoepenRequest;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Getter
@Builder
public class ChineesPoepen {
    public static final int NUMBER_OF_ROUNDS = 17;
    public static final int NUMBER_OF_PLAYERS = 4;
    public static final String type = "CHINEES POEPEN";

    private String id;
    private String host;
    private Set<String> players;
    private int round;
    // @Todo: Create Object for scores
    private Map<String, List<Score>> scores;
    private LocalDateTime startTime;
    private String state;

    public void setId(String id) {
        this.id = id;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int toNextRound() {
        return ++this.round;
    }

    public Set<UUID> getPlayers() {
        return this.players.stream()
                .map(id -> UUID.fromString(id))
                .collect(Collectors.toSet());
    }

    public Optional<Integer> getScore(String playerId) {
         List<Score> playerScores = this.scores.get(playerId);

         if (playerScores == null) {
             return Optional.empty();
         } else {
             return Optional.of(
                     playerScores.stream()
                        .mapToInt(playerScore -> playerScore.getScore())
                        .sum());
         }
    }

    public void setScores(List<ChineesPoepenRequest.Score> scoresList) {
        this.scores = new HashMap();
        scoresList.stream()
                .map(score -> this.scores.put(score.getPlayer(), createScore(score.getRounds(), score.getRound())));
    }

    public List<Score> getRoundScores() {
        return getRoundScores(this.getRound());
    }

    public List<Score> getRoundScores(int round) {
        validateRoundNumber(round);

        List<Score> scoreList = new ArrayList<>();
        for (List<Score> playerScores : this.scores.values() ) {
            try {
                scoreList.add(playerScores.get(round - 1));
            } catch(IndexOutOfBoundsException e) {
                String errorMessage = String.format("Scores for round %d was not set for all players.", round);
                throw new IndexOutOfBoundsException(errorMessage);
            }
        }

        return scoreList;
    }

    public boolean roundScoresCalledValid() {
        return this.roundScoresCalledValid(this.getRound());
    }

    public boolean roundScoresCalledValid(int round) {
        validateRoundNumber(round);

        int sum = this.getRoundScores().stream()
                .mapToInt(score -> score.pointsCalled)
                .sum();

        return sum != getHandSizeForRound(round);
    }

    public boolean roundScoresScoredValid() {
        return this.roundScoresScoredValid(this.getRound());
    }

    public boolean roundScoresScoredValid(int round) {
        validateRoundNumber(round);

        int sum = this.getRoundScores().stream()
                .mapToInt(score -> score.pointsScored)
                .sum();

        return sum == getHandSizeForRound(round);
    }

    private List<Score> createScore(List<ChineesPoepenRequest.Round> score, int round) {
        return score.stream()
                .map(sc -> new Score(sc.getPointsCalled(), sc.getPointsScored(), round))
                .collect(Collectors.toList());
    }

    public static class Score {
        public Score(int pointsCalled, int pointsScored, int round) {
            this.pointsCalled = pointsCalled;
            this.pointsScored = pointsScored;
            this.score = getScore();
            this.round = round;
        }

        int pointsCalled;
        int pointsScored;
        int round;

        int score;

        public int getScore() {
            int diff = Math.abs(this.pointsCalled - this.pointsScored);
            int bonus = (this.round >= 6 && this.round <= 12 && this.pointsCalled == 0) ? 10 : 5;

            return this.pointsCalled == this.pointsScored ? bonus + this.pointsCalled : -diff;
        }
    }

    private int getHandSizeForRound(int round) {
        validateRoundNumber(round);

        if (round < 10 ) {
            return round;
        } else {
            return 8 - round % 10;
        }
    }

    private void validateRoundNumber(int round) {
        if (round < 1 || round > NUMBER_OF_ROUNDS) {
            String errorMessage = String.format("Round number should be greater than 1 and smaller or equal to %d", NUMBER_OF_ROUNDS);
            throw new IllegalArgumentException(errorMessage);
        }
    }
}
