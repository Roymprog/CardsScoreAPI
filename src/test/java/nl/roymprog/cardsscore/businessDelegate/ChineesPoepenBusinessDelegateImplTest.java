package nl.roymprog.cardsscore.businessDelegate;

import com.google.common.collect.Maps;
import nl.roymprog.cardsscore.mocks.ChineesPoepenObjectFactory;
import nl.roymprog.cardsscore.mocks.MockFactory;
import nl.roymprog.cardsscore.mocks.PlayersObjectFactory;
import nl.roymprog.cardsscore.models.ChineesPoepen;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

@RunWith(MockitoJUnitRunner.class)
public class ChineesPoepenBusinessDelegateImplTest {

  ChineesPoepenBusinessDelegateImpl sut = new ChineesPoepenBusinessDelegateImpl();


  @Test
  public void playRound() {
    ChineesPoepen cp = ChineesPoepenObjectFactory.getChineesPoepen();

    playRound(cp);
  }

  @Test(expected = IllegalArgumentException.class)
  public void playRoundIncompleteScores() {
    ChineesPoepen cp = MockFactory.newChineesPoepen();

    playRound(cp);
  }

  @Test
  public void playRoundInvalidScoresCalled() {
    ChineesPoepen cp = MockFactory.newChineesPoepenInvalidScoresCalled();
    try {
      playRound(cp);
    } catch (IllegalArgumentException e) {
      assertEquals(String.format("Sum of called cannot equal hand size for round %d", cp.getRound()), e.getMessage());
      return;
    }

    fail("Expected IllegalArgumentException not thrown");
  }

  @Test
  public void playRoundInvalidScoresScored() {
    ChineesPoepen cp = MockFactory.newChineesPoepenInvalidScoresScored();
    try {
      playRound(cp);
    } catch (IllegalArgumentException e) {
      assertEquals(String.format("Sum of scored points has to equal hand size for round %d", cp.getRound()), e.getMessage());
      return;
    }

    fail("Expected IllegalArgumentException not thrown");
  }

  @Test
  public void playFullGame() {
    ChineesPoepen cp = ChineesPoepenObjectFactory.getChineesPoepenFullGame();
//      simulate playing x amount of rounds
    for (int i = 1; i <= ChineesPoepen.NUMBER_OF_ROUNDS; i++) {
      sut.validateRound(cp.getScores(i), i);
    }

    Map<String, List<ChineesPoepen.Score>> players = Maps.transformValues(cp.getScores(), scores -> sut.calculateScores(scores));
    ChineesPoepen result = ChineesPoepen.builder().scores(players).build();

    Optional<Integer> score = result.getScore(PlayersObjectFactory.PLAYER_1.getId());
    assertEquals(63, score.get().intValue());
    score = result.getScore(PlayersObjectFactory.PLAYER_2.getId());
    assertEquals(31, score.get().intValue());
    score = result.getScore(PlayersObjectFactory.PLAYER_3.getId());
    assertEquals(43, score.get().intValue());
    score = result.getScore(PlayersObjectFactory.PLAYER_4.getId());
    assertEquals(51, score.get().intValue());
  }

  private void playRound(ChineesPoepen cp ) {
    sut.validateRound(cp.getScores(cp.getRound()), cp.getRound());
  }
}
