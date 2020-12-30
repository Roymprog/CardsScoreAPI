package nl.roymprog.cardsscore.controllers;

import nl.roymprog.cardsscore.database.ChineesPoepenDbInterface;
import nl.roymprog.cardsscore.database.UsersDbInterface;
import nl.roymprog.cardsscore.models.ChineesPoepen;
import nl.roymprog.cardsscore.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
// @TODO: proper CORS management on Application level
@CrossOrigin
@RequestMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UsersController {

  private ChineesPoepenDbInterface chineesPoepenDb;
  private UsersDbInterface usersDb;

  @Autowired
  public UsersController(ChineesPoepenDbInterface chineesPoepenDb, UsersDbInterface usersDb) {
    this.chineesPoepenDb = chineesPoepenDb;
    this.usersDb = usersDb;
  }

  @GetMapping
  public ResponseEntity<List<User>> getUsers() {
    return new ResponseEntity<>(usersDb.getAllUsers(), HttpStatus.OK);
  }

  @GetMapping(value = "/{userId}/games")
  public ResponseEntity<List<ChineesPoepen>> getGames(@PathVariable String userId) {
    return new ResponseEntity<>(chineesPoepenDb.getGames(userId), HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<User> login(@RequestBody User user) {
    if (user.getName() == null || user.getName().length() < 3 ) {
      return new ResponseEntity<>(user, HttpStatus.BAD_REQUEST);
    }

    User loggedInUser = usersDb.getUser(user.getName())
                          .orElseGet(() -> usersDb.insertNew(new User(user.getName())));

    return new ResponseEntity<>(loggedInUser, HttpStatus.OK);
  }
}
