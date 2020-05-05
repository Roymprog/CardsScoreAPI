package nl.roymprog.cardsscore.database;

import nl.roymprog.cardsscore.database.mongo.models.ChineesPoepenConverter;
import nl.roymprog.cardsscore.database.mongo.models.ChineesPoepenEntity;
import nl.roymprog.cardsscore.database.mongo.repositories.ChineesPoepenRepository;
import nl.roymprog.cardsscore.models.ChineesPoepen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ChineesPoepenMongoDb implements ChineesPoepenDbInterface {

  private ChineesPoepenRepository repository;

  @Autowired
  public ChineesPoepenMongoDb(ChineesPoepenRepository repository, MongoTemplate mongoTemplate) {
    this.repository = repository;
  }

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

  public void deleteAll() {
    repository.deleteAll();
  }

  public List<ChineesPoepen> getGames(String userId) {
    return repository.findByPlayers(userId).stream()
            .map(ChineesPoepenConverter::toDto)
            .collect(Collectors.toList());
  }
}
