package nl.roymprog.cardsscore.database.mongo.models;

import nl.roymprog.cardsscore.mocks.MockFactory;
import nl.roymprog.cardsscore.models.ChineesPoepen;
import org.junit.Assert;
import org.junit.Test;

import java.util.stream.Collectors;

public class ChineesPoepenConverterTest {

  @Test
  public void toDtoTest() {
    ChineesPoepenEntity entity = MockFactory.newChineesPoepenEntity();
    ChineesPoepen cp = ChineesPoepenConverter.toDto(entity);

    Assert.assertEquals(entity.id, cp.getId());
    Assert.assertEquals(entity.host, cp.getHost());
    Assert.assertEquals(entity.round, cp.getRound());
    Assert.assertEquals(entity.scores.keySet(), cp.getPlayers().stream().map(player -> player.getId()).collect(Collectors.toSet()));
  }

  @Test
  public void toEntityTest() {
    ChineesPoepen cp = MockFactory.newChineesPoepen();
    ChineesPoepenEntity entity = ChineesPoepenConverter.toEntity(cp);

    Assert.assertEquals(null, entity.id);
    Assert.assertEquals(cp.getHost(), entity.host);
    Assert.assertEquals(cp.getRound(), entity.round);
    Assert.assertEquals(cp.getPlayers().stream().map(player -> player.getId()).collect(Collectors.toSet()), entity.scores.keySet());
  }
}