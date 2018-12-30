package nl.roymprog.cardsscore.resources;

import nl.roymprog.cardsscore.controllers.UsersController;
import nl.roymprog.cardsscore.models.Game;
import nl.roymprog.cardsscore.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UsersResources {

    UsersController usersController;

    public UsersResources(UsersController usersController) {
        this.usersController = usersController;
    }

    @GetMapping
    public List<User> getUsers() {
        return usersController.getUsers();
    }

    @PostMapping
    public User register(@RequestBody User user) {
        User newUser = usersController.createUser(user);

        return newUser;
    }

    @GetMapping("/{userId}")
    public User getUser(@PathVariable String userId) {
        return usersController.getUser(userId);
    }

    @PostMapping("/{userId}/games")
    public Game newGame(@PathVariable String userId) {
        User user = usersController.getUser(userId);

        Game newGame = usersController.createGameForUser(user);

        return newGame;
    }

    @GetMapping("/{userId}/games")
    public List<Game> getGamesForUser(@PathVariable String userId) {
        return usersController.getGames(userId);
    }
}
