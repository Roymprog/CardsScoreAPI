package nl.roymprog.cardsscore.database.mongo.repositories;

import nl.roymprog.cardsscore.database.mongo.models.ChineesPoepenEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface ChineesPoepenRepository extends MongoRepository<ChineesPoepenEntity, String> {
  Optional<ChineesPoepenEntity> findById(String id);

  // Fetches all entities containing the playerId in the list of players
  List<ChineesPoepenEntity> findByPlayersId(String playerId);
}
