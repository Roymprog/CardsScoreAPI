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
import nl.roymprog.cardsscore.database.ChineesPoepenMongoDb;
import nl.roymprog.cardsscore.database.mongo.MongoDbConfig;
import nl.roymprog.cardsscore.models.ChineesPoepen;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.io.IOException;
import java.util.*;

@SpringBootTest
@ContextConfiguration
public class ChineesPoepenRepositoryTest {
  @Autowired
  private ChineesPoepenMongoDb db;

  @Autowired
  private MongoDbConfig mongoDbConfig;

  private static final MongodStarter starter = MongodStarter.getDefaultInstance();

  private MongodExecutable _mongodExe;
  private MongodProcess _mongod;

  private MongoClient _mongo;

  @BeforeClass
  public void setUp() throws IOException {

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

    ChineesPoepen.Score roundScore = new ChineesPoepen.Score(6, 1, 1);
    List<ChineesPoepen.Score> score = new ArrayList<>();
    score.add(roundScore);
    Map<String, List<ChineesPoepen.Score>> players = new HashMap<>();
    players.put(host, score);
    players.put(host1, score);
    players.put(host2, score);
    players.put(host3, score);


    // given
    ChineesPoepen cp =
            ChineesPoepen.builder()
              .host(host)
              .round(1)
              .scores(players)
              .build();

    // when
    ChineesPoepen saved = db.insertNew(cp);

    // then
    Optional<ChineesPoepen> optionalCp = db.getGame(saved.getId());
    assert optionalCp.isPresent();

//    assertThat(repository.findById(eut.id).get().players).hasSize(3);
  }
}