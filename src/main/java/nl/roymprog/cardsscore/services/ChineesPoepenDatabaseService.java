package nl.roymprog.cardsscore.services;

import lombok.extern.java.Log;
import nl.roymprog.cardsscore.models.ChineesPoepen;
import nl.roymprog.cardsscore.models.entity.ChineesPoepenConverter;
import nl.roymprog.cardsscore.models.entity.ChineesPoepenEntity;
import nl.roymprog.cardsscore.repositories.ChineesPoepenDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@Log
public class ChineesPoepenDatabaseService implements ChineesPoepenDbInterface {

    private ChineesPoepenDAO dao;

    @Autowired
    public ChineesPoepenDatabaseService(ChineesPoepenDAO dao) {
        this.dao = dao;
    }

    @Override
    public ChineesPoepen insertNew(ChineesPoepen cp) {
        cp.setId(UUID.randomUUID().toString());
        ChineesPoepenEntity entity = ChineesPoepenConverter.toEntity(cp);
        try {
            return ChineesPoepenConverter.toDto(dao.save(entity));
        } catch (DataAccessException e) {
            e.printStackTrace();
            log.severe(e.getMessage());
            throw new RuntimeException("Something went wrong inserting new entry in database");
        }
    }

    @Override
    public Optional<ChineesPoepenEntity> getGame(String gameId) {
        return dao.findById(UUID.fromString(gameId));
    }

//    List<ChineesPoepenResponse> games = new ArrayList<>();
//
//    public Optional<ChineesPoepenResponse> getGame(String id) {
//        return games.stream()
//                .filter(game -> game.getId().equals(id))
//                .findAny();
//    }
//
//    public List<ChineesPoepenResponse> getGames() {
//        return games;
//    }
//
//    public List<ChineesPoepenResponse> getGamesForUser(String userId) {
//        return getGames().stream()
//            .filter(game -> game.getHostId().equals(userId))
//            .collect(Collectors.toList());
//    }
//
//    public ChineesPoepenResponse insertGame(ChineesPoepenResponse game) {
//        games.add(game);
//
//        return game;
//    }
}
