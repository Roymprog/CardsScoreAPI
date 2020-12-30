package nl.roymprog.cardsscore.database.mongo.models;

import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@NoArgsConstructor
public class UserEntity {
  @Id
  public String id;

  public UserEntity(String name) {
    this.name = name;
  }

  public UserEntity(String id, String name) {
    this.id = id;
    this.name = name;
  }

  public String name;
}
