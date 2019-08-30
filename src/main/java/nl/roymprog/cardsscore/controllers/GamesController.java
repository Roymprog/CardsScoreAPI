package nl.roymprog.cardsscore.controllers;

import nl.roymprog.cardsscore.configuration.chineespoepensm.ChineesPoepenEvents;
import nl.roymprog.cardsscore.configuration.chineespoepensm.ChineesPoepenStateMachineFactory;
import nl.roymprog.cardsscore.configuration.chineespoepensm.ChineesPoepenStates;
import nl.roymprog.cardsscore.models.dto.ChineesPoepenDTO;
import nl.roymprog.cardsscore.models.entity.ChineesPoepen;
import nl.roymprog.cardsscore.models.entity.Game;
import nl.roymprog.cardsscore.models.entity.Player;
import nl.roymprog.cardsscore.repositories.ChineesPoepenRepository;
import nl.roymprog.cardsscore.resources.request.GamesRequestBody;
import nl.roymprog.cardsscore.resources.response.GameResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.StateMachine;
import org.springframework.stereotype.Controller;

import java.util.Optional;
import java.util.UUID;

@Controller
public class GamesController {

    private final ChineesPoepenRepository gamesRepository;
    private final ChineesPoepenStateMachineFactory factory;

    @Autowired
    public GamesController(ChineesPoepenRepository repository, ChineesPoepenStateMachineFactory factory) {
        this.gamesRepository = repository;
        this.factory = factory;
    }

    public Iterable<ChineesPoepen> getGames() {
        return gamesRepository.findAll();
    }

    public Game getGame(UUID id) {
        Optional<ChineesPoepen> optionalGame = gamesRepository.findById(id);

        if (!optionalGame.isPresent()) {
            throw new IllegalArgumentException("Could not get game with id" + id);
        }

        return optionalGame.get();
    }

    public GameResponse createGame(GamesRequestBody body) {
        ChineesPoepen game = new ChineesPoepen();
        body.getPlayerIds()
                .forEach(id -> game.addPlayers(new Player(id)));

        ChineesPoepenDTO dto = new ChineesPoepenDTO();
        dto.setEvent(ChineesPoepenEvents.CREATE);
        // No means of authenticating host yet
        dto.setHostId(UUID.randomUUID());

        StateMachine<ChineesPoepenStates, ChineesPoepenEvents> sm = this.factory.change(dto);
        sm.start();

        game.setGameState(sm.getState().getId());

        GameResponse response = new GameResponse();

        return response;
    }

//    public Game createHistoricGame(String hostId, String stringType) {
//        GameTypes type = Arrays.stream(GameTypes.values())
//                .filter(gameTypes -> gameTypes.getName().equals(stringType))
//                .findAny()
//                .get();
//
//        Game newGame;
//
//        switch (type) {
//            case CHINEES_POEPEN:
//                newGame = new ChineesPoepen(UuidUtil.generateRandomId(), hostId);
//                break;
//            default:
//                throw new IllegalArgumentException("Could not match game type, cannot create game");
//        }
//
//        gamesDatabaseService.insertGame(newGame);
//
//        return newGame;
//    }

}
