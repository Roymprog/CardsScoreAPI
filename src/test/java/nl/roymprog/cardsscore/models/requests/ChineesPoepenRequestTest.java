package nl.roymprog.cardsscore.models.requests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import nl.roymprog.cardsscore.models.ChineesPoepen;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ChineesPoepenRequestTest {
  private final ObjectMapper om = new ObjectMapper().registerModule(new Jdk8Module());

  @Test
  public void validRequest() throws IOException {
    InputStream is = getClass().getResourceAsStream("/fixtures/ChineesPoepenRequest/valid.json");
    ChineesPoepenRequest cpr = om.readValue(is, ChineesPoepenRequest.class);

    assertEquals(ChineesPoepen.NUMBER_OF_PLAYERS, cpr.getScores().size());
    ChineesPoepenRequest.Score player1Scores = cpr.getScores().get("18adb080-acae-42e8-9984-c9bf97259306");
    assertEquals(1, cpr.getRound());
    assertEquals(0, player1Scores.getPointsCalled());
    assertEquals(Optional.of(1), player1Scores.getPointsScored());
    ChineesPoepenRequest.Score player2Scores = cpr.getScores().get("18adb080-acae-42e8-9984-c9bf97259307");
    assertEquals(0, player2Scores.getPointsCalled());
    assertEquals(Optional.empty(), player2Scores.getPointsScored());
  }

  @Test
  public void validSerialisationRequest() throws IOException {
    String expected = new String(getClass().getResourceAsStream("/fixtures/ChineesPoepenRequest/valid.json")
            .readAllBytes()).replaceAll("\\s", "");

    ChineesPoepenRequest cpr = new ChineesPoepenRequest();
    cpr.setRound(1);
    Map<String, ChineesPoepenRequest.Score> map = new HashMap<>();
    ChineesPoepenRequest.Score score = new ChineesPoepenRequest.Score();
    score.setPointsCalled(0);
    map.put("18adb080-acae-42e8-9984-c9bf97259301", score);
    map.put("18adb080-acae-42e8-9984-c9bf97259307", score);
    map.put("18adb080-acae-42e8-9984-c9bf97259309", score);
    ChineesPoepenRequest.Score score2 = new ChineesPoepenRequest.Score();
    score2.setPointsScored(1);
    score2.setPointsCalled(0);
    map.put("18adb080-acae-42e8-9984-c9bf97259306", score2);
    cpr.setScores(map);
    String result = om.writeValueAsString(cpr);
    assertEquals(expected, result);
  }
}