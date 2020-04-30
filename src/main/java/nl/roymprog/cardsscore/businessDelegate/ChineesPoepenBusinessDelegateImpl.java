package nl.roymprog.cardsscore.businessDelegate;

import lombok.extern.java.Log;
import nl.roymprog.cardsscore.models.ChineesPoepen;
import nl.roymprog.cardsscore.models.requests.ChineesPoepenCreateRequest;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

@Component
@Log
public class ChineesPoepenBusinessDelegateImpl implements ChineesPoepenBusinessDelegate {
    public ChineesPoepen createGame(ChineesPoepenCreateRequest dto) {
        ChineesPoepen cp = ChineesPoepen.builder()
                            .host(dto.getHost())
                            .players(dto.getPlayers())
                            .round(1)
                            .startTime(LocalDateTime.now())
                            .state("CREATED")
                            .scores(new HashMap<>())
                            .build();

        // TODO: allow for determining dealer in backend
        if ( cp.getPlayers().size() < ChineesPoepen.NUMBER_OF_PLAYERS ) {
            String message = "Cannot create game, number of players is not " + ChineesPoepen.NUMBER_OF_PLAYERS;
            log.severe(message);
            throw new IllegalArgumentException(message);
        }

        return cp;
    }

    public ChineesPoepen playRound(ChineesPoepen cp) {
        List<ChineesPoepen.Score> scoreList = cp.getRoundScores();

        if (scoreList.size() != cp.NUMBER_OF_PLAYERS) {
            String errorMessage = String.format("Scores for round %d have not been set for all players", cp.getRound());
            throw new IllegalArgumentException(errorMessage);
        }

        if (!cp.roundScoresCalledValid()) {
            String errorMessage = String.format("Sum of called cannot equal hand size for round %d", cp.getRound());
            throw new IllegalArgumentException(errorMessage);
        }

        if (!cp.roundScoresScoredValid()) {
            String errorMessage = String.format("Sum of scored points has to equal hand size for round %d", cp.getRound());
            throw new IllegalArgumentException(errorMessage);
        }

        cp.toNextRound();

        return cp;
    }
}
