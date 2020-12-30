package nl.roymprog.cardsscore.database.mongo.models;

import nl.roymprog.cardsscore.models.User;

public class UserConverter {
  public static UserEntity toEntity(User user) {
    return new UserEntity(user.getId(), user.getName());
  }

  public static User toDto(UserEntity entity) {
    return new User(entity.id, entity.name);
  }
}
