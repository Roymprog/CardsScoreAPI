package nl.roymprog.cardsscore.database.mongo.repositories;

import nl.roymprog.cardsscore.database.mongo.models.ChineesPoepenEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;
import java.util.stream.Stream;

public interface ChineesPoepenRepository extends MongoRepository<ChineesPoepenEntity, String> {
  Optional<ChineesPoepenEntity> findById(String id);

  Stream<ChineesPoepenEntity> findByHost(String host);
}
