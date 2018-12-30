package nl.roymprog.cardsscore.controllers;

import nl.roymprog.cardsscore.models.Game;
import nl.roymprog.cardsscore.models.User;
import nl.roymprog.cardsscore.services.GamesDatabaseService;
import nl.roymprog.cardsscore.util.UuidUtil;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
public class GamesController {

    GamesDatabaseService gamesDatabaseService;

    public GamesController(GamesDatabaseService gamesDatabaseService) {
        this.gamesDatabaseService = gamesDatabaseService;
    }

    public List<Game> getGames() {
        return gamesDatabaseService.getGames();
    }

    public Game getGame(String id) {
        Optional<Game> optionalGame = gamesDatabaseService.getGame(id);

        if (!optionalGame.isPresent()) {
            throw new IllegalArgumentException("Could not get game with id" + id);
        }

        return optionalGame.get();
    }

    public Game createGame(String hostId) {
        Game newGame = new Game(UuidUtil.generateRandomId(), hostId);

        gamesDatabaseService.insertGame(newGame);

        return newGame;
    }

    public List<Game> getGamesForUser(String userId) {
        return gamesDatabaseService.getGamesForUser(userId);
    }
}
