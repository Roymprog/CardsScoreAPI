package nl.roymprog.cardsscore.controllers;

import javassist.NotFoundException;
import nl.roymprog.cardsscore.businessDelegate.ChineesPoepenBusinessDelegate;
import nl.roymprog.cardsscore.businessDelegate.ChineesPoepenBusinessDelegateImpl;
import nl.roymprog.cardsscore.models.ChineesPoepen;
import nl.roymprog.cardsscore.models.entity.ChineesPoepenEntity;
import nl.roymprog.cardsscore.models.requests.ChineesPoepenCreateRequest;
import nl.roymprog.cardsscore.models.requests.ChineesPoepenRequest;
import nl.roymprog.cardsscore.models.response.ChineesPoepenResponse;
import nl.roymprog.cardsscore.services.ChineesPoepenDbInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping(value = "/users/{userId}/games", produces = MediaType.APPLICATION_JSON_VALUE)
public class ChineesPoepenController {

    private ChineesPoepenDbInterface chineesPoepenDbService;
    private ChineesPoepenBusinessDelegate chineesPoepenBusinessDelegateImpl;

    @Autowired
    public ChineesPoepenController(ChineesPoepenDbInterface chineesPoepenDbService,
                                   ChineesPoepenBusinessDelegate chineesPoepenBusinessDelegateImpl) {
        this.chineesPoepenDbService = chineesPoepenDbService;
        this.chineesPoepenBusinessDelegateImpl = chineesPoepenBusinessDelegateImpl;
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
    public ResponseEntity<ChineesPoepen> newGame(@PathVariable String userId, @Valid @RequestBody ChineesPoepenCreateRequest req) {
        ChineesPoepen cp = chineesPoepenBusinessDelegateImpl.createGame(userId, req);
        ChineesPoepen response = chineesPoepenDbService.insertNew(cp);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{gameId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ChineesPoepenResponse> play(@PathVariable String userId, @PathVariable String gameId, @Valid @RequestBody ChineesPoepenRequest req) throws Exception {
        Optional<ChineesPoepenEntity> chineesPoepenEntityOptional = chineesPoepenDbService.getGame(gameId);

        if (!chineesPoepenEntityOptional.isPresent()) {
            throw new NotFoundException("Could not find game with id: " + gameId);
        }

        ChineesPoepenEntity chineesPoepenEntity = chineesPoepenEntityOptional.get();

        if (chineesPoepenEntity.getHost().equals(UUID.fromString(userId))) {
            throw new Exception("Not allowed to update game, host mismatch");
        }

//        chineesPoepen.play();

        return null;
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