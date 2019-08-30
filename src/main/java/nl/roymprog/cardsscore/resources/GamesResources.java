package nl.roymprog.cardsscore.resources;

import nl.roymprog.cardsscore.controllers.GamesController;
import nl.roymprog.cardsscore.models.entity.ChineesPoepen;
import nl.roymprog.cardsscore.models.entity.Game;
import nl.roymprog.cardsscore.resources.request.GamesRequestBody;
import nl.roymprog.cardsscore.resources.response.GameResponse;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/games")
public class GamesResources {

    private GamesController gamesController;

    public GamesResources(GamesController gamesController) {
        this.gamesController = gamesController;
    }

    @GetMapping
    public Iterable<ChineesPoepen> getGames() {
        return gamesController.getGames();
    }

    @GetMapping("/{id}")
    public Game getGame(@PathVariable UUID id) {
        return gamesController.getGame(id);
    }

    @PostMapping("/")
    public GameResponse create(@RequestBody GamesRequestBody body) {
        return gamesController.createGame(body);
    }
}