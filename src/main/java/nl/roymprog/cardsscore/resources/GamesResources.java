package nl.roymprog.cardsscore.resources;

import nl.roymprog.cardsscore.controllers.GamesController;
import nl.roymprog.cardsscore.models.Game;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/games")
public class GamesResources {

    GamesController gamesController;

    public GamesResources(GamesController gamesController) {
        this.gamesController = gamesController;
    }

    @GetMapping
    public List<Game> getGames() {
        return gamesController.getGames();
    }

    @GetMapping("/{id")
    public Game getGame(@PathVariable String id) {
        return gamesController.getGame(id);
    }
}