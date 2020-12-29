package nl.roymprog.cardsscore.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class User {

  private String id;
  private final String name;

  public User(String name) {
    this.name = name;
  }
}
