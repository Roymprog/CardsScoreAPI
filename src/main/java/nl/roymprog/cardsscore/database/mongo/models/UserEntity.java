package nl.roymprog.cardsscore.database.mongo.models;

import org.springframework.data.annotation.Id;

public class UserEntity {
  @Id
  public String id;

  public UserEntity(String name) {
    this.name = name;
  }

  public final String name;
}
