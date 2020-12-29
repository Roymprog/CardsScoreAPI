package nl.roymprog.cardsscore.database.mongo.repositories;

import nl.roymprog.cardsscore.database.mongo.models.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UsersRepository extends MongoRepository<UserEntity, String> {
  Optional<UserEntity> findByName(String id);
}
