package nl.roymprog.cardsscore.database;

import nl.roymprog.cardsscore.database.mongo.models.ChineesPoepenConverter;
import nl.roymprog.cardsscore.database.mongo.models.ChineesPoepenEntity;
import nl.roymprog.cardsscore.database.mongo.repositories.ChineesPoepenRepository;
import nl.roymprog.cardsscore.models.ChineesPoepen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ChineesPoepenMongoDb implements ChineesPoepenDbInterface {

  @Autowired
  ChineesPoepenRepository repository;

  public ChineesPoepen insertNew(ChineesPoepen chineesPoepen) {
    ChineesPoepenEntity entity = ChineesPoepenConverter.toEntity(chineesPoepen);
    ChineesPoepenEntity savedEntity = repository.insert(entity);
    return ChineesPoepenConverter.toDto(savedEntity);
  }

  public Optional<ChineesPoepen> getGame(String gameId) {
    return repository.findById(gameId)
      .map(entity -> ChineesPoepenConverter.toDto(entity));
  }

  public ChineesPoepen updateGame(ChineesPoepen chineesPoepen) {
    ChineesPoepenEntity entity = ChineesPoepenConverter.toEntity(chineesPoepen);
    return getGame(entity.id)
            .map(game -> repository.save(entity))
            .map(ChineesPoepenConverter::toDto)
            .orElseThrow(() -> new IllegalArgumentException(String.format("Game not found with id: %s", chineesPoepen.getId())));
  }
}
