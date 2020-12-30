package nl.roymprog.cardsscore.database;

import nl.roymprog.cardsscore.models.User;

import java.util.List;
import java.util.Optional;

public interface UsersDbInterface {
  User insertNew(User user);

  Optional<User> getUser(String name);

  Optional<User> getUserById(String id);

  List<User> getAllUsers();
}
