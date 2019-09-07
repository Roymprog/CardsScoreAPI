package nl.roymprog.cardsscore.controllers;

import nl.roymprog.cardsscore.models.entity.ChineesPoepenEntity;
import nl.roymprog.cardsscore.models.requests.ChineesPoepenCreateRequest;
import nl.roymprog.cardsscore.models.requests.ChineesPoepenRequest;
import nl.roymprog.cardsscore.models.response.ChineesPoepenResponse;
import nl.roymprog.cardsscore.services.ChineesPoepenDbInterface;
import nl.roymprog.cardsscore.util.UuidUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.omg.PortableServer.REQUEST_PROCESSING_POLICY_ID;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static org.hamcrest.Matchers.any;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ChineesPoepenControllerTest {

    private ChineesPoepenController controller;

    @Mock
    private ChineesPoepenDbInterface db;

    private final UUID USER_ID = UuidUtil.generateRandomId();
    private final UUID GAME_ID = UuidUtil.generateRandomId();
    private final UUID PLAYER1 = UuidUtil.generateRandomId();
    private final UUID PLAYER2 = UuidUtil.generateRandomId();
    private final UUID PLAYER3 = UuidUtil.generateRandomId();
    private Set<String> players = new HashSet<>();
    private ChineesPoepenEntity chineesPoepenEntity;
    private ChineesPoepenCreateRequest req;

    @Before
    public void setUp() {
        controller = new ChineesPoepenController(db);
        players.add(PLAYER1.toString());
        players.add(PLAYER2.toString());
        players.add(PLAYER3.toString());
        chineesPoepenEntity = new ChineesPoepenEntity();
        chineesPoepenEntity.setHost(USER_ID);
        chineesPoepenEntity.setId(GAME_ID);
        req = new ChineesPoepenCreateRequest();
        req.setPlayers(players);
    }

    @Test
    public void newGame() {
        when(db.createGame(anyString(), anySet(), anyInt())).thenReturn(chineesPoepenEntity);

        ResponseEntity<ChineesPoepenResponse> responseEntity = controller.newGame(USER_ID.toString(), req);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }

    @Test(expected = IllegalArgumentException.class)
    public void newGamePlayersShort() {
        req.setPlayers(new HashSet<>());
        controller.newGame(USER_ID.toString(), req);
    }

    @Test(expected = IllegalArgumentException.class)
    public void newGameDbIAException() {
        when(db.createGame(anyString(), anySet(), anyInt())).thenThrow(IllegalArgumentException.class);

        controller.newGame(USER_ID.toString(), req);
    }
}