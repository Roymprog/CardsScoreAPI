package nl.roymprog.cardsscore.webmvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import nl.roymprog.cardsscore.controllers.ChineesPoepenController;
import nl.roymprog.cardsscore.models.entity.ChineesPoepenEntity;
import nl.roymprog.cardsscore.models.requests.ChineesPoepenCreateRequest;
import nl.roymprog.cardsscore.models.response.ChineesPoepenResponse;
import nl.roymprog.cardsscore.services.ChineesPoepenDbInterface;
import nl.roymprog.cardsscore.util.UuidUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.io.InputStream;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ChineesPoepenController.class)
public class ChineesPoepenRequestTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ChineesPoepenDbInterface db;

    private final String USER_ID = UuidUtil.generateRandomId().toString();
    private final String GAME_ID = UuidUtil.generateRandomId().toString();
    private final ObjectMapper om = new ObjectMapper();

    @Test
    public void badRequestWhenNoPlayers() throws Exception {

        InputStream inputStream = getClass().getResourceAsStream("/fixtures/ChineesPoepenCreateRequest/invalid.json");
        ChineesPoepenCreateRequest req = om.readValue(inputStream, ChineesPoepenCreateRequest.class);

        this.mockMvc.perform(
                post("/users/" + USER_ID + "/games")
                    .content(om.writeValueAsString(req))
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isBadRequest());
    }

    @Test
    public void created() throws Exception {
        ChineesPoepenEntity entity = new ChineesPoepenEntity();
        entity.setId(UUID.fromString(GAME_ID));
        entity.setHost(UUID.fromString(USER_ID));

        when(db.createGame(anyString(), anySet(), anyInt())).thenReturn(entity);

        InputStream inputStream = getClass().getResourceAsStream("/fixtures/ChineesPoepenCreateRequest/valid.json");
        ChineesPoepenCreateRequest req = om.readValue(inputStream, ChineesPoepenCreateRequest.class);

        this.mockMvc.perform(
                post("/users/" + USER_ID + "/games")
                        .content(om.writeValueAsString(req))
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isCreated())
        .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
    }
}
