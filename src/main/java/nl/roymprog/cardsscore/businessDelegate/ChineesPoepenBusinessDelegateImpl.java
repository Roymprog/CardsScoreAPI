package nl.roymprog.cardsscore.businessDelegate;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import nl.roymprog.cardsscore.models.ChineesPoepen;
import nl.roymprog.cardsscore.models.requests.ChineesPoepenCreateRequest;
import nl.roymprog.cardsscore.services.ChineesPoepenDbInterface;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashMap;

@Component
@Log
public class ChineesPoepenBusinessDelegateImpl implements ChineesPoepenBusinessDelegate {
    public ChineesPoepen createGame(String host, ChineesPoepenCreateRequest dto) {
        ChineesPoepen cp = ChineesPoepen.builder()
                            .host(host)
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
}
