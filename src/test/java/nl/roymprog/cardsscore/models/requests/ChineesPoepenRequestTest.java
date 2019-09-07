package nl.roymprog.cardsscore.models.requests;

import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class ChineesPoepenRequestTest {

    @Test
    public void getTotal() {
        int n = 4;
        int points = 1;
        ChineesPoepenRequest.Score score = new ChineesPoepenRequest.Score();
        score.setScore(points);
        List<ChineesPoepenRequest.Score> scores = Collections.nCopies(n, score);

        ChineesPoepenRequest request = new ChineesPoepenRequest();
        request.setScores(scores);

        assertEquals(request.getTotal(), n*points);
    }

    @Test
    public void noScores() {
        ChineesPoepenRequest request = new ChineesPoepenRequest();

        assertEquals(request.getTotal(), 0);
    }
}