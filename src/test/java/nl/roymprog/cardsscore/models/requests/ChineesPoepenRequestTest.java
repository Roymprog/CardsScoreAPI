package nl.roymprog.cardsscore.models.requests;

import com.fasterxml.jackson.databind.ObjectMapper;
import nl.roymprog.cardsscore.models.ChineesPoepen;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class ChineesPoepenRequestTest {
    private final ObjectMapper om = new ObjectMapper();

    @Test
    public void validRequest() throws IOException {
        InputStream is = getClass().getResourceAsStream("/fixtures/ChineesPoepenRequest/valid.json");
        ChineesPoepenRequest cpr = om.readValue(is, ChineesPoepenRequest.class);

        assertEquals(ChineesPoepen.NUMBER_OF_PLAYERS, cpr.getScores().size());
        ChineesPoepenRequest.Score player1Scores = cpr.getScores("18adb080-acae-42e8-9984-c9bf97259306").get();
        List<ChineesPoepenRequest.Round> player1Rounds = player1Scores.getRounds();
        ChineesPoepenRequest.Round player1Round = player1Rounds.get(0);
        assertEquals(1, player1Rounds.size());
        assertEquals(1, player1Round.getRound());
        assertEquals(0, player1Round.getPointsCalled());
        assertEquals(1, player1Round.getPointsScored());
        ChineesPoepenRequest.Score player2Scores = cpr.getScores("18adb080-acae-42e8-9984-c9bf97259307").get();
        List<ChineesPoepenRequest.Round> player2Rounds = player2Scores.getRounds();
        ChineesPoepenRequest.Round player2Round = player2Rounds.get(0);
        assertEquals(1, player2Rounds.size());
        assertEquals(1, player2Round.getRound());
        assertEquals(0, player2Round.getPointsCalled());
        assertEquals(0, player2Round.getPointsScored());
    }
}