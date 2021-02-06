package nl.roymprog.cardsscore.controllers;

import com.google.common.collect.Maps;
import nl.roymprog.cardsscore.businessDelegate.ChineesPoepenBusinessDelegate;
import nl.roymprog.cardsscore.database.ChineesPoepenDbInterface;
import nl.roymprog.cardsscore.database.UsersDbInterface;
import nl.roymprog.cardsscore.models.ChineesPoepen;
import nl.roymprog.cardsscore.models.User;
import nl.roymprog.cardsscore.models.requests.ChineesPoepenCreateRequest;
import nl.roymprog.cardsscore.models.requests.ChineesPoepenRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/games", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin
public class ChineesPoepenController {

  private ChineesPoepenDbInterface chineesPoepenDbService;
  private UsersDbInterface userDbService;
  private ChineesPoepenBusinessDelegate chineesPoepenBusinessDelegateImpl;

  @Autowired
  public ChineesPoepenController(ChineesPoepenDbInterface chineesPoepenDbService,
                                 UsersDbInterface userDbService,
                                 ChineesPoepenBusinessDelegate chineesPoepenBusinessDelegateImpl) {
    this.chineesPoepenDbService = chineesPoepenDbService;
    this.userDbService = userDbService;
    this.chineesPoepenBusinessDelegateImpl = chineesPoepenBusinessDelegateImpl;
  }

  // @TODO: Validate players exist
  // @TODO: Return proper player names for display
  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<ChineesPoepen> newGame(@Valid @RequestBody ChineesPoepenCreateRequest req) {
    Set<User> users =
            req.getPlayers().stream()
              .map((player) -> userDbService.getUserById(player))
              .filter(opt -> opt.isPresent())
              .map(opt -> opt.get())
              .collect(Collectors.toSet());

    ChineesPoepen request = ChineesPoepen.builder()
            .host(req.getHost())
            .players(users)
            .startTime(LocalDateTime.now())
            .state("CREATED")
            .build();

    ChineesPoepen cp = chineesPoepenBusinessDelegateImpl.createGame(request);
    ChineesPoepen response = chineesPoepenDbService.insertNew(cp);

    return new ResponseEntity<>(response, HttpStatus.CREATED);
  }

  @PutMapping(value = "/{gameId}", consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<ChineesPoepen> play(@PathVariable String gameId,
                                            @Valid @RequestBody ChineesPoepenRequest req) throws Exception {
    Optional<ChineesPoepen> chineesPoepenOptional = chineesPoepenDbService.getGame(gameId);

    if (!chineesPoepenOptional.isPresent()) {
      throw new IllegalArgumentException("Could not find game with id: " + gameId);
    }

    ChineesPoepen cp = chineesPoepenOptional.get();

    Map<String, ChineesPoepen.Score> scoreMap = Maps.transformValues(req.getScores(),
            score -> score.getPointsScored()
                    .map(pointsScored -> new ChineesPoepen.Score(score.getPointsCalled(), pointsScored))
                    .orElse(new ChineesPoepen.Score(score.getPointsCalled())));

    List<ChineesPoepen.Score> scores = new ArrayList<>(scoreMap.values());
    chineesPoepenBusinessDelegateImpl.validateRound(scores, req.getRound());

    Map<String, ChineesPoepen.Score> scm = Maps.transformValues(scoreMap, sc -> chineesPoepenBusinessDelegateImpl.calculateScore(sc, req.getRound()));
    cp.addScores(scm, req.getRound());

    ChineesPoepen saved = chineesPoepenDbService.updateGame(cp);

    return new ResponseEntity<>(saved, HttpStatus.OK);
  }

  // @TODO: Return real names
  @GetMapping(value = "/{gameId}")
  public ResponseEntity<ChineesPoepen> getGame(@PathVariable String gameId) {
    Optional<ChineesPoepen> chineesPoepenEntityOptional = chineesPoepenDbService.getGame(gameId);

    if (!chineesPoepenEntityOptional.isPresent()) {
      throw new IllegalArgumentException("Could not find game with id: " + gameId);
    }

    ChineesPoepen cp = chineesPoepenEntityOptional.get();

    return new ResponseEntity<>(cp, HttpStatus.OK);
  }

}