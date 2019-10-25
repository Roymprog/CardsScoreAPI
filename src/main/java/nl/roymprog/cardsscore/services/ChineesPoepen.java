package nl.roymprog.cardsscore.services;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import nl.roymprog.cardsscore.models.Game;
import nl.roymprog.cardsscore.models.RoundScore;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

@NoArgsConstructor
public class ChineesPoepen implements Game {

    public final static int NUMBER_OF_PLAYERS = 4;

    private Player getPlayer(UUID id) {
        return this.players.stream()
                .filter(player -> player.getId().equals(id))
                .findAny()
                .get();
    }

    private Set<Player> players;
    private List<RoundScore> roundScore;
    private Round round;
    private int totalPointsCalled = 0;
    private int totalPointsScored = 0;

    @Override
    public Game playRound() {

        this.roundScore.forEach(score -> {
            if (score.getPointsCalled() < 0) {
                throw new IllegalArgumentException("Points called has to be a positive integer");
            }

            this.totalPointsCalled = this.totalPointsCalled + score.getPointsCalled();
            this.totalPointsScored = this.totalPointsScored + score.getPointsScored();

            if (this.totalPointsCalled == this.totalPointsScored) {
                if (round.getCards() < 6) {
                    score.setScore(5 + score.getPointsCalled());
                } else {
                    score.setScore(10);
                }
            } else {
                score.setScore(Math.negateExact(Math.abs(this.totalPointsCalled - this.totalPointsScored)));
            }
        });

        if (this.totalPointsCalled == this.round.getCards()) {
            throw new IllegalArgumentException("Amount of points called cannot be the same as the round number played");
        }

        if (this.totalPointsScored != this.round.getCards()) {
            throw new IllegalArgumentException("Amount of points scored has to equal the round number");
        }

        // Add new scores to game
        this.roundScore.forEach(score -> {
//            Todo: handle case where UUIDs in gamescore are different from uuids roundscores
            Player player = this.getPlayer(score.getPlayerId());
            player.addScore(round, score.getPointsCalled(), score.getPointsScored(), score.getScore());
        });

        return this;
    }

    public enum Round {
        ROUND1(1, 1),
        ROUND2(2, 2),
        ROUND3(3, 3),
        ROUND4(4, 4),
        ROUND5(5, 5),
        ROUND6(6, 6),
        ROUND7(7, 7),
        ROUND8(8, 8),
        ROUND9(9, 9),
        ROUND10(8, 10),
        ROUND11(7, 11),
        ROUND12(6, 12),
        ROUND13(5, 13),
        ROUND14(4, 14),
        ROUND15(3, 15),
        ROUND16(2, 16),
        ROUND17(1, 17);

        Round(int cards, int round) {
            this.cards = cards;
            this.round = round;
        }

        private int cards;

        private int round;

        public int getRound() {
            return round;
        }

        public int getCards() {
            return cards;
        }
    }

    public class Player {
        private UUID id;

        Map<Round, Score> score;

        public UUID getId() {
            return id;
        }

        public void addScore(Round round, int pointsCalled, int pointsScored, int points ) {
            Score score = new Score(pointsCalled, pointsScored, points);
            this.score.put(round, score);
        }

        @AllArgsConstructor
        private class Score {
            int pointsCalled;
            int pointsScored;
            int points;
        }
    }
}
