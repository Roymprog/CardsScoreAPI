package nl.roymprog.cardsscore.database;

import nl.roymprog.cardsscore.models.ChineesPoepen;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ChineesPoepenMongoDb implements ChineesPoepenDbInterface {
    public ChineesPoepen insertNew(ChineesPoepen chineesPoepen) {
        return chineesPoepen;
    }

    public Optional<ChineesPoepen> getGame(String gameId) {
        return Optional.empty();
    }

    public ChineesPoepen updateGame(ChineesPoepen chineesPoepen) {
        return chineesPoepen;
    }
}
