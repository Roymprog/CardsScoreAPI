package nl.roymprog.cardsscore.controllers;

import nl.roymprog.cardsscore.models.ChineesPoepen;
import nl.roymprog.cardsscore.models.entity.ChineesPoepenEntity;
import nl.roymprog.cardsscore.models.requests.ChineesPoepenCreateRequest;
import nl.roymprog.cardsscore.models.response.ChineesPoepenResponse;
import nl.roymprog.cardsscore.services.ChineesPoepenDbInterface;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(value = "/users/{userId}/games", produces = MediaType.APPLICATION_JSON_VALUE)
public class ChineesPoepenController {

    private ChineesPoepenDbInterface chineesPoepenDbService;

    public ChineesPoepenController(ChineesPoepenDbInterface db) {
        this.chineesPoepenDbService = db;
    }

//    @GetMapping
//    public List<ChineesPoepenResponse> getGames(@PathVariable String userId) {
//        return buildChineesPoepenResponse(chineesPoepenDAO.findByHost(UUID.fromString(userId)));
//    }
//
//    @GetMapping("/{id}")
//    public ChineesPoepenResponse getGame(@PathVariable String userId, @PathVariable String id) {
//        ChineesPoepenEntity entity = chineesPoepenDAO.getOne(UUID.fromString(id));
//
//        return new ChineesPoepenResponse(entity.getId().toString(), entity.getHost().toString());
//    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ChineesPoepenResponse> newGame(@PathVariable String userId, @Valid @RequestBody ChineesPoepenCreateRequest req) {
        Set<String> players = req.getPlayers();
        players.add(userId);

        if ( players.size() < ChineesPoepen.NUMBER_OF_PLAYERS ) {
            throw new IllegalArgumentException("Cannot create game, number of players is not " + ChineesPoepen.NUMBER_OF_PLAYERS);
        }

        // TODO: allow for determining dealer in backend
//        Iterator it = playerIds.iterator();
//        int counter = generateRandomIntIntRange(0, 3);
//        while(it.hasNext()) {
//            if (counter == 0) {
//                entity.setDealer(UUID.fromString((String) it.next()));
//            }
//        }

        ChineesPoepenEntity entity = chineesPoepenDbService.createGame(userId, players, 1);

        ChineesPoepenResponse response = new ChineesPoepenResponse(entity.getId().toString(), userId, entity.getPlayers(), entity.getRound());
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    private List<ChineesPoepenResponse> buildChineesPoepenResponse(Iterable<ChineesPoepenEntity> entities) {
        List<ChineesPoepenResponse> responses = new LinkedList<>();
        Iterator entityIterator = entities.iterator();

        while (entityIterator.hasNext()) {
            ChineesPoepenEntity entity = (ChineesPoepenEntity) entityIterator.next();
            responses.add(new ChineesPoepenResponse(entity.getId().toString(), entity.getHost().toString()));
        }

        return responses;
    }
}