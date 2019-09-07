package nl.roymprog.cardsscore.services;

import nl.roymprog.cardsscore.models.entity.ChineesPoepenEntity;
import nl.roymprog.cardsscore.models.response.ChineesPoepenResponse;
import nl.roymprog.cardsscore.repositories.ChineesPoepenDAO;
import nl.roymprog.cardsscore.util.UuidUtil;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ChineesPoepenDatabaseService implements ChineesPoepenDbInterface{

    private ChineesPoepenDAO dao;

    public ChineesPoepenDatabaseService(ChineesPoepenDAO dao) {
        this.dao = dao;
    }

    @Override
    public ChineesPoepenEntity createGame(String hostId, Set<String> players, int round) {
        ChineesPoepenEntity entity = new ChineesPoepenEntity();
        entity.setId(UuidUtil.generateRandomId());
        entity.setHost(UUID.fromString(hostId));
        entity.setRound(1);

        Set<UUID> playerIds = players.stream()
                .map(player -> UUID.fromString(player))
                .collect(Collectors.toSet());
        entity.setPlayers(playerIds);

        return dao.save(entity);
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
