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
import nl.roymprog.cardsscore.database.mongo.models.MockFactory;
import nl.roymprog.cardsscore.models.ChineesPoepen;
import org.junit.AfterClass;
import org.junit.Assert;
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
  public void insertTest() {
    ChineesPoepen cp = MockFactory.newChineesPoepen();

    // when
    ChineesPoepen saved = db.insertNew(cp);

    // then
    Optional<ChineesPoepen> optionalCp = db.getGame(saved.getId());
    assert optionalCp.isPresent();
    ChineesPoepen fetched = optionalCp.get();
    Assert.assertEquals(cp.getPlayers(), fetched.getPlayers());
  }

  @Test
  public void updateTest() {
    // given
    ChineesPoepen cp = MockFactory.newChineesPoepen();

    ChineesPoepen saved = db.insertNew(cp);
    saved.toNextRound();

    // when
    ChineesPoepen updated = db.updateGame(saved);

    // then
    Optional<ChineesPoepen> optionalCp = db.getGame(updated.getId());
    assert optionalCp.isPresent();
    ChineesPoepen sut = optionalCp.get();
    Assert.assertEquals(2, sut.getRound());
  }
}