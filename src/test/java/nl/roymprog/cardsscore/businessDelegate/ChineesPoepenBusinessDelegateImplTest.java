package nl.roymprog.cardsscore.businessDelegate;

import nl.roymprog.cardsscore.models.ChineesPoepen;
import nl.roymprog.cardsscore.models.requests.ChineesPoepenObjectFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ChineesPoepenBusinessDelegateImplTest {

    @Mock
    ChineesPoepen cpMock;

    @Mock
    List<ChineesPoepen.Score> listMock;

    ChineesPoepenBusinessDelegateImpl sut = new ChineesPoepenBusinessDelegateImpl();

    @Test
    public void playRound() {
        ChineesPoepen cp = ChineesPoepenObjectFactory.getChineesPoepen();
        int round = cp.getRound();

        cp = sut.playRound(cp);

        assertEquals(round + 1, cp.getRound());
        // @Todo: Tests new scores
    }

    @Test(expected = IllegalArgumentException.class)
    public void playRoundIncompleteScores() {
        when(cpMock.getRoundScores()).thenReturn(Collections.emptyList());

        sut.playRound(cpMock);
    }

    @Test
    public void playRoundInvalidScoresCalled() {
        int ROUND = 1;

        when(cpMock.getRoundScores()).thenReturn(listMock);
        when(listMock.size()).thenReturn(ChineesPoepen.NUMBER_OF_PLAYERS);
        when(cpMock.roundScoresCalledValid()).thenReturn(false);
        when(cpMock.getRound()).thenReturn(ROUND);

        try {
            sut.playRound(cpMock);
        } catch( IllegalArgumentException e) {
            assertEquals(String.format("Sum of called cannot equal hand size for round %d", ROUND), e.getMessage());
            return;
        }

        fail("Expected IllegalArgumentException not thrown");
    }

    @Test
    public void playRoundInvalidScoresScored() {
        int ROUND = 1;

        when(cpMock.getRoundScores()).thenReturn(listMock);
        when(listMock.size()).thenReturn(ChineesPoepen.NUMBER_OF_PLAYERS);
        when(cpMock.roundScoresScoredValid()).thenReturn(false);
        when(cpMock.roundScoresCalledValid()).thenReturn(true);
        when(cpMock.getRound()).thenReturn(ROUND);

        try {
            sut.playRound(cpMock);
        } catch( IllegalArgumentException e) {
            assertEquals(String.format("Sum of scored points has to equal hand size for round %d", ROUND), e.getMessage());
            return;
        }

        fail("Expected IllegalArgumentException not thrown");
    }

    @Test
    public void playFullGame() {
        ChineesPoepen cp = ChineesPoepenObjectFactory.getChineesPoepenFullGame();
//      simulate playing x amount of rounds
        for(int i = 0; i < ChineesPoepen.NUMBER_OF_ROUNDS; i++) {
            sut.playRound(cp);
        }
    }
}
