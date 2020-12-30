package nl.roymprog.cardsscore.database;

import nl.roymprog.cardsscore.database.mongo.models.UserConverter;
import nl.roymprog.cardsscore.database.mongo.models.UserEntity;
import nl.roymprog.cardsscore.database.mongo.repositories.UsersRepository;
import nl.roymprog.cardsscore.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class UsersDbMongoImpl implements UsersDbInterface {

  private UsersRepository repository;

  @Autowired
  public UsersDbMongoImpl(UsersRepository repository) {
    this.repository = repository;
  }

  public User insertNew(User user) {
    UserEntity userEntity = repository.insert(new UserEntity(user.getName()));
    return new User(userEntity.id, userEntity.name);
  }

  public Optional<User> getUser(String name) {
    return repository.findByName(name)
            .map(UserConverter::toDto);
  }

  public Optional<User> getUserById(String id) {
    return repository.findById(id)
            .map(UserConverter::toDto);
  }

  public List<User> getAllUsers() {
    return repository.findAll()
            .stream().map(UserConverter::toDto)
            .collect(Collectors.toList());
  }
}
