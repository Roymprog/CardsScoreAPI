package nl.roymprog.cardsscore.controllers;

import nl.roymprog.cardsscore.database.ChineesPoepenDbInterface;
import nl.roymprog.cardsscore.models.ChineesPoepen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/users/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
public class UsersController {

  private ChineesPoepenDbInterface db;

  @Autowired
  public UsersController(ChineesPoepenDbInterface db) {
    this.db = db;
  }

  @GetMapping(value = "/games")
  public ResponseEntity<List<ChineesPoepen>> getGames(@PathVariable String userId) {
    return new ResponseEntity<>(db.getGames(userId), HttpStatus.OK);
  }
}
