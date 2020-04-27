package nl.roymprog.cardsscore.database.mongo.repositories;

import nl.roymprog.cardsscore.database.mongo.models.ChineesPoepenEntity;
import nl.roymprog.cardsscore.database.mongo.models.ChineesPoepenRoundScore;
import nl.roymprog.cardsscore.database.mongo.models.ChineesPoepenScore;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
@ExtendWith(SpringExtension.class)
public class ChineesPoepenRepositoryTest {
    @Autowired ChineesPoepenRepository repository;

    @Test
    public void test() {
        String host = "host";
        String host1 = "host1";
        String host2 = "host2";
        String host3 = "host3";

        ChineesPoepenRoundScore roundScore = new ChineesPoepenRoundScore(6,1,1);
        Map<Integer, ChineesPoepenRoundScore> roundScores = new HashMap<>();
        roundScores.put(1, roundScore);
        ChineesPoepenScore score = new ChineesPoepenScore(roundScores);
        Map<String, ChineesPoepenScore> players = new HashMap<>();
        players.put(host1, score);
        players.put(host2, score);
        players.put(host3, score);

        // given
        ChineesPoepenEntity entity = new ChineesPoepenEntity(host, 0, players);

        // when
        ChineesPoepenEntity eut = repository.save(entity);

        // then
        Stream<ChineesPoepenEntity> es = repository.findByHost(host);
        assertThat(es).extracting("players")
                .hasSize(1);

        assertThat(repository.findById(eut.id).get().players).hasSize(3);
    }
}