package nl.roymprog.cardsscore.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import nl.roymprog.cardsscore.businessDelegate.ChineesPoepenBusinessDelegate;
import nl.roymprog.cardsscore.database.ChineesPoepenDbInterface;
import nl.roymprog.cardsscore.models.ChineesPoepen;
import nl.roymprog.cardsscore.models.requests.ChineesPoepenCreateRequest;
import nl.roymprog.cardsscore.models.requests.ChineesPoepenRequest;
import nl.roymprog.cardsscore.util.UuidUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = ChineesPoepenController.class)
public class ChineesPoepenControllerTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ChineesPoepenDbInterface chineesPoepenDbService;

    @MockBean
    private ChineesPoepenBusinessDelegate chineesPoepenBusinessDelegateImpl;

    private final String USER_ID = UuidUtil.generateRandomId().toString();
    private final String GAME_ID = UuidUtil.generateRandomId().toString();
    private final String PLAYER1 = UuidUtil.generateRandomId().toString();
    private final String PLAYER2 = UuidUtil.generateRandomId().toString();
    private final String PLAYER3 = UuidUtil.generateRandomId().toString();
    private Set<String> players;
    private ChineesPoepenCreateRequest createRequest = new ChineesPoepenCreateRequest();
    private ChineesPoepenRequest updateRequest;

    @Before
    public void setUp() throws IOException {
        players = new HashSet<>();
        players.add(PLAYER1);
        players.add(PLAYER2);
        players.add(PLAYER3);
        players.add(USER_ID);

        createRequest.setPlayers(players);
        InputStream is = getClass().getResourceAsStream("/fixtures/ChineesPoepenRequest/valid.json");
        updateRequest = objectMapper.readValue(is, ChineesPoepenRequest.class);
    }

    @Test
    public void newGameIsCreated() throws Exception {
        this.mockMvc.perform(post("/games", USER_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(createRequest)))
                .andExpect(status().isCreated());
    }

    @Test
    public void newGameNoPlayers() throws Exception {
        createRequest.setPlayers(null);

        this.mockMvc.perform(post("/games", USER_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(createRequest)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void createGameInvalid() throws Exception {
        when(chineesPoepenBusinessDelegateImpl.createGame(any(ChineesPoepenCreateRequest.class))).thenThrow(IllegalArgumentException.class);

        this.mockMvc.perform(post("/games", USER_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(createRequest)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void playGameInvalidGameId() throws Exception {
        when(chineesPoepenDbService.getGame(anyString())).thenReturn(Optional.empty());

        this.mockMvc.perform(put("/games/{gameId}", USER_ID, GAME_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updateRequest)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void playGameInvalidRound() throws Exception {
        Set<String> correctPlayers = new HashSet<>();
        correctPlayers.add("18adb080-acae-42e8-9984-c9bf97259306");
        correctPlayers.add("18adb080-acae-42e8-9984-c9bf97259307");
        correctPlayers.add("18adb080-acae-42e8-9984-c9bf97259308");
        correctPlayers.add("18adb080-acae-42e8-9984-c9bf97259309");
        ChineesPoepen cp = ChineesPoepen.builder()
                .id(GAME_ID)
                .host(USER_ID)
                .players(correctPlayers)
                .build();

        when(chineesPoepenDbService.getGame(anyString())).thenReturn(Optional.of(cp));
        when(chineesPoepenBusinessDelegateImpl.playRound(any(ChineesPoepen.class))).thenThrow(IllegalArgumentException.class);

        this.mockMvc.perform(put("/games/{gameId}", USER_ID, GAME_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updateRequest)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void playGame() throws Exception {
        Set<String> correctPlayers = new HashSet<>();
        correctPlayers.add("18adb080-acae-42e8-9984-c9bf97259306");
        correctPlayers.add("18adb080-acae-42e8-9984-c9bf97259307");
        correctPlayers.add("18adb080-acae-42e8-9984-c9bf97259308");
        correctPlayers.add("18adb080-acae-42e8-9984-c9bf97259309");
        ChineesPoepen cp = ChineesPoepen.builder()
                .id(GAME_ID)
                .host(USER_ID)
                .players(correctPlayers)
                .round(1)
                .build();
        cp.setScores(updateRequest.getScores());

        when(chineesPoepenDbService.getGame(anyString())).thenReturn(Optional.of(cp));
        when(chineesPoepenBusinessDelegateImpl.playRound(any(ChineesPoepen.class))).thenReturn(cp);
        when(chineesPoepenDbService.updateGame(any(ChineesPoepen.class))).thenReturn(cp);

        this.mockMvc.perform(put("/games/{gameId}", USER_ID, GAME_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updateRequest)))
                .andExpect(status().isOk());
    }

}
