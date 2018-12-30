package nl.roymprog.cardsscore.controllers;

import nl.roymprog.cardsscore.models.Game;
import nl.roymprog.cardsscore.models.User;
import nl.roymprog.cardsscore.services.UsersDatabaseService;
import nl.roymprog.cardsscore.util.UuidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class UsersController {

    UsersDatabaseService usersDatabaseService;

    GamesController gamesController;

    public UsersController(UsersDatabaseService usersDatabaseService, GamesController gamesController) {
        this.usersDatabaseService = usersDatabaseService;
        this.gamesController = gamesController;
    }

    public User createUser(User user) {
        if (user == null) {
            user = new User();
        }

        Optional<User> userOptional = usersDatabaseService.getUser(user.getId());

        if (userOptional.isPresent()) {
            throw new IllegalArgumentException("Cannot register user with this id, user already exists");
        }

        user.setJoinedOn(LocalDateTime.now());
        user.setId(UuidUtil.generateRandomId());
        user.setGames(new ArrayList<>());

        return usersDatabaseService.insertUser(user);
    }

    public List<User> getUsers() {
        return usersDatabaseService.getUserList();
    }

    public User getUser(String id) {

        Optional<User> userOptional = usersDatabaseService.getUser(id);

        if (!userOptional.isPresent()) {
            throw new IllegalArgumentException("User could not be found for id: " + id);
        }

        return userOptional.get();
    }

    public Game createGameForUser(User host) {
        Game game = gamesController.createGame(host.getId());

        host.getGames().add(game);

        return game;
    }

    public List<Game> getGames(String userId) {
        return gamesController.getGamesForUser(userId);
    }
}
