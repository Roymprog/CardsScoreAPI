package nl.roymprog.cardsscore.models.requests;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ChineesPoepenCreateRequestTest {
  private final ObjectMapper om = new ObjectMapper();

  @Test
  public void parseChineesPoepenCreateRequest() throws IOException {
    InputStream input = getClass().getResourceAsStream("/fixtures/ChineesPoepenCreateRequest/valid.json");
    ChineesPoepenCreateRequest req = om.readValue(input, ChineesPoepenCreateRequest.class);

    assertEquals(4, req.getPlayers().size());
    assertTrue(req.getPlayers().contains("18adb080-acae-42e8-9984-c9bf97259301"));
    assertTrue(req.getPlayers().contains("18adb080-acae-42e8-9984-c9bf97259306"));
    assertTrue(req.getPlayers().contains("18adb080-acae-42e8-9984-c9bf97259307"));
    assertTrue(req.getPlayers().contains("18adb080-acae-42e8-9984-c9bf97259309"));
  }

  @Test
  public void parseInvalidChineesPoepenCreateRequest() throws IOException {
    InputStream input = getClass().getResourceAsStream("/fixtures/ChineesPoepenCreateRequest/invalid.json");
    ChineesPoepenCreateRequest req = om.readValue(input, ChineesPoepenCreateRequest.class);

    assertEquals(2, req.getPlayers().size());
    assertTrue(req.getPlayers().contains("1"));
    assertTrue(req.getPlayers().contains("3"));
  }
}