package nl.roymprog.cardsscore.resources;

import nl.roymprog.cardsscore.controllers.GamesController;
import nl.roymprog.cardsscore.controllers.UsersController;
import nl.roymprog.cardsscore.models.entity.ChineesPoepen;
import nl.roymprog.cardsscore.models.entity.Game;
import nl.roymprog.cardsscore.models.entity.User;
import nl.roymprog.cardsscore.resources.request.GamesRequestBody;
import nl.roymprog.cardsscore.resources.response.GameResponse;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UsersResources {

    UsersController usersController;

    GamesController gamesController;

    public UsersResources(UsersController usersController, GamesController gamesController) {
        this.usersController = usersController;
        this.gamesController = gamesController;
    }

    @GetMapping
    public List<User> getUsers() {
        return usersController.getUsers();
    }

    @GetMapping("/{userId}")
    public User getUser(@PathVariable UUID userId) {
        return usersController.getUser(userId);
    }

    @PostMapping("/{userId}/games")
    public GameResponse newGame(@PathVariable UUID userId, @RequestBody GamesRequestBody gamesRequestBody) {
        User user = usersController.getUser(userId);

        GameResponse newGame = gamesController.createGame(gamesRequestBody);

        return newGame;
    }

//    @PostMapping("/{userId}/historic-game")
//    public Game newHistoricGame(@PathVariable String userId, @RequestBody GamesRequestBody gamesRequestBody) {
//        User user = usersController.getUser(userId);
//
//        Game newGame = gamesController.createHistoricGame(userId, gamesRequestBody.getGameType());
//
//        return newGame;
//    }

    @GetMapping("/{userId}/games")
    public List<ChineesPoepen> getGamesForUser(@PathVariable UUID userId) {
        return Collections.EMPTY_LIST;
    }
}
