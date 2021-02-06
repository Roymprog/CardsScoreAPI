package nl.roymprog.cardsscore.database.mongo.models;

import nl.roymprog.cardsscore.mocks.MockFactory;
import nl.roymprog.cardsscore.models.ChineesPoepen;
import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ChineesPoepenConverterTest {

  @Test
  public void toDtoTest() {
    ChineesPoepenEntity entity = MockFactory.newChineesPoepenEntity();
    ChineesPoepen cp = ChineesPoepenConverter.toDto(entity);

    assertEquals(entity.id, cp.getId());
    assertEquals(entity.host, cp.getHost());
    assertEquals(entity.round, cp.getRound());
    assertEquals(entity.scores.keySet(), cp.getPlayers().stream().map(player -> player.getId()).collect(Collectors.toSet()));
  }

  @Test
  public void toEntityTest() {
    ChineesPoepen cp = MockFactory.newChineesPoepen();
    ChineesPoepenEntity entity = ChineesPoepenConverter.toEntity(cp);

    assertEquals(null, entity.id);
    assertEquals(cp.getHost(), entity.host);
    assertEquals(cp.getRound(), entity.round);
    assertEquals(cp.getPlayers().stream().map(player -> player.getId()).collect(Collectors.toSet()), entity.scores.keySet());
  }
}