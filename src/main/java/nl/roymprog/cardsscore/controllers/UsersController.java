package nl.roymprog.cardsscore.controllers;

import nl.roymprog.cardsscore.models.entity.User;
import nl.roymprog.cardsscore.services.UsersDatabaseService;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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

        return usersDatabaseService.insertUser(user);
    }

    public List<User> getUsers() {
        return usersDatabaseService.getUserList();
    }

    public User getUser(UUID id) {

        Optional<User> userOptional = usersDatabaseService.getUser(id);

        if (!userOptional.isPresent()) {
            throw new IllegalArgumentException("User could not be found for id: " + id);
        }

        return userOptional.get();
    }
}
