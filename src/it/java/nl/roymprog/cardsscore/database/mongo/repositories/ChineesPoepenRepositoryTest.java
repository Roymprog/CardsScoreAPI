package nl.roymprog.cardsscore.database.mongo.repositories;

import com.mongodb.MongoClient;
import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodProcess;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.IMongodConfig;
import de.flapdoodle.embed.mongo.config.MongodConfigBuilder;
import de.flapdoodle.embed.mongo.config.Net;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.runtime.Network;
import nl.roymprog.cardsscore.database.mongo.MongoDbConfig;
import nl.roymprog.cardsscore.database.mongo.models.ChineesPoepenEntity;
import nl.roymprog.cardsscore.database.mongo.models.ChineesPoepenRoundScore;
import nl.roymprog.cardsscore.database.mongo.models.ChineesPoepenScore;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ContextConfiguration
public class ChineesPoepenRepositoryTest {
    @Autowired
    private ChineesPoepenRepository repository;

    @Autowired
    private MongoDbConfig mongoDbConfig;

  private MongodExecutable _mongodExe;
  private MongodProcess _mongod;

  private MongoClient _mongo;

    @BeforeClass
    public void setUp() throws IOException {
      MongodStarter starter = MongodStarter.getDefaultInstance();

      IMongodConfig mongodConfig = new MongodConfigBuilder()
                .version(Version.Main.PRODUCTION)
                .net(new Net(mongoDbConfig.getHost(), mongoDbConfig.getPort(), Network.localhostIsIPv6()))
                .build();

        _mongodExe = starter.prepare(mongodConfig);
        _mongod = _mongodExe.start();

        _mongo = new MongoClient(mongodConfig.net().getBindIp(), mongodConfig.net().getPort());

    }

    @AfterClass
    protected void tearDown() {
      _mongod.stop();
      _mongodExe.stop();
    }

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