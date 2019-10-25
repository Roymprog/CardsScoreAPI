package nl.roymprog.cardsscore.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import nl.roymprog.cardsscore.businessDelegate.ChineesPoepenBusinessDelegate;
import nl.roymprog.cardsscore.businessDelegate.ChineesPoepenBusinessDelegateImpl;
import nl.roymprog.cardsscore.models.ChineesPoepen;
import nl.roymprog.cardsscore.models.Game;
import nl.roymprog.cardsscore.models.entity.ChineesPoepenEntity;
import nl.roymprog.cardsscore.models.requests.ChineesPoepenCreateRequest;
import nl.roymprog.cardsscore.services.ChineesPoepenDbInterface;
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

import java.util.HashSet;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
    private final String PLAYER1 = UuidUtil.generateRandomId().toString();
    private final String PLAYER2 = UuidUtil.generateRandomId().toString();
    private final String PLAYER3 = UuidUtil.generateRandomId().toString();
    private Set<String> players;
    private ChineesPoepenCreateRequest req = new ChineesPoepenCreateRequest();

    @Before
    public void setUp() {
        players = new HashSet<>();
        players.add(PLAYER1);
        players.add(PLAYER2);
        players.add(PLAYER3);
        players.add(USER_ID);

        req.setPlayers(players);
    }

    @Test
    public void newGameIsCreated() throws Exception {
        this.mockMvc.perform(post("/users/{userId}/games", USER_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(req)))
                .andExpect(status().isCreated());
    }

    @Test
    public void newGameNoPlayers() throws Exception {
        req.setPlayers(null);

        this.mockMvc.perform(post("/users/{userId}/games", USER_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(req)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void createGameInvalid() throws Exception {
        when(chineesPoepenBusinessDelegateImpl.createGame(anyString(), any(ChineesPoepenCreateRequest.class))).thenThrow(IllegalArgumentException.class);

        this.mockMvc.perform(post("/users/{userId}/games", USER_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(req)))
                .andExpect(status().isBadRequest());
    }

//    @Test
//    public void newGameDbError() throws Exception {
//        when(chineesPoepenDbService.insertNew(any(ChineesPoepen.class))).thenThrow(RuntimeException.class);
//
//        this.mockMvc.perform(post("/users/{userId}/games", USER_ID)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(req)))
//                .andExpect(status().isInternalServerError());
//    }
}
