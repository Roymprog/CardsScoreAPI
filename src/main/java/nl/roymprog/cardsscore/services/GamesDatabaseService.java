package nl.roymprog.cardsscore.services;

import nl.roymprog.cardsscore.models.Game;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GamesDatabaseService {

    List<Game> games = new ArrayList<>();

    public Optional<Game> getGame(String id) {
        return games.stream()
                .filter(game -> game.getId().equals(id))
                .findAny();
    }

    public List<Game> getGames() {
        return games;
    }

    public List<Game> getGamesForUser(String userId) {
        return getGames().stream()
            .filter(game -> game.getHostId().equals(userId))
            .collect(Collectors.toList());
    }

    public Game insertGame(Game game) {
        games.add(game);

        return game;
    }
}
