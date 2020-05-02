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
    ChineesPoepenEntity savedEntity = repository.save(entity);
    return ChineesPoepenConverter.toDto(savedEntity);
  }

  public Optional<ChineesPoepen> getGame(String gameId) {
    return repository.findById(gameId)
      .map(entity -> ChineesPoepenConverter.toDto(entity));
  }

  public ChineesPoepen updateGame(ChineesPoepen chineesPoepen) {
    return chineesPoepen;
  }
}
