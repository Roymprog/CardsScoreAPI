package nl.roymprog.cardsscore.controllers;

import com.google.common.collect.Maps;
import nl.roymprog.cardsscore.businessDelegate.ChineesPoepenBusinessDelegate;
import nl.roymprog.cardsscore.database.ChineesPoepenDbInterface;
import nl.roymprog.cardsscore.models.ChineesPoepen;
import nl.roymprog.cardsscore.models.requests.ChineesPoepenCreateRequest;
import nl.roymprog.cardsscore.models.requests.ChineesPoepenRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/games", produces = MediaType.APPLICATION_JSON_VALUE)
public class ChineesPoepenController {

  private ChineesPoepenDbInterface chineesPoepenDbService;
  private ChineesPoepenBusinessDelegate chineesPoepenBusinessDelegateImpl;

  @Autowired
  public ChineesPoepenController(ChineesPoepenDbInterface chineesPoepenDbService,
                                 ChineesPoepenBusinessDelegate chineesPoepenBusinessDelegateImpl) {
    this.chineesPoepenDbService = chineesPoepenDbService;
    this.chineesPoepenBusinessDelegateImpl = chineesPoepenBusinessDelegateImpl;
  }

  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<ChineesPoepen> newGame(@Valid @RequestBody ChineesPoepenCreateRequest req) {
    ChineesPoepen cp = chineesPoepenBusinessDelegateImpl.createGame(req);
    ChineesPoepen response = chineesPoepenDbService.insertNew(cp);

    return new ResponseEntity<>(response, HttpStatus.CREATED);
  }

  @PutMapping(value = "/{gameId}", consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<ChineesPoepen> play(@PathVariable String gameId,
                                            @Valid @RequestBody ChineesPoepenRequest req) throws Exception {
    Optional<ChineesPoepen> chineesPoepenEntityOptional = chineesPoepenDbService.getGame(gameId);

    if (!chineesPoepenEntityOptional.isPresent()) {
      throw new IllegalArgumentException("Could not find game with id: " + gameId);
    }

    ChineesPoepen cp = chineesPoepenEntityOptional.get();

    Map<String, ChineesPoepen.Score> scoreMap = Maps.transformValues(req.getScores(),
            score -> new ChineesPoepen.Score(score.getPointsCalled(), score.getPointsScored(), cp.getRound())
    );

    List<ChineesPoepen.Score> scores = scoreMap.values().stream()
            .collect(Collectors.toList());
    chineesPoepenBusinessDelegateImpl.validateRound(scores, req.getRound());

    Map<String, ChineesPoepen.Score> scm = Maps.transformValues(scoreMap, sc -> chineesPoepenBusinessDelegateImpl.calculateScore(sc, req.getRound()));
    cp.addScores(scm);

    ChineesPoepen saved = chineesPoepenDbService.updateGame(cp);

    return new ResponseEntity<>(saved, HttpStatus.OK);
  }

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