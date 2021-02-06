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
import nl.roymprog.cardsscore.mocks.MockFactory;
import nl.roymprog.cardsscore.models.ChineesPoepen;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static nl.roymprog.cardsscore.mocks.ScoresObjectFactory.getRoundScore;
import static nl.roymprog.cardsscore.mocks.ScoresObjectFactory.getRoundScoreCalled;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class ChineesPoepenRepositoryTest {
  @Autowired
  private ChineesPoepenMongoDb db;

  @Autowired
  private MongoDbConfig mongoDbConfig;

  private static final MongodStarter starter = MongodStarter.getDefaultInstance();

  private static MongodExecutable _mongodExe;
  private static MongodProcess _mongod;

  private static MongoClient _mongo;

  @BeforeAll
  public static void setUp() throws IOException {
    MongoDbConfig mongoDbConfig = new MongoDbConfig();

    IMongodConfig mongodConfig = new MongodConfigBuilder()
            .version(Version.Main.PRODUCTION)
            .net(new Net(mongoDbConfig.getHost(), mongoDbConfig.getPort(), Network.localhostIsIPv6()))
            .build();

    _mongodExe = starter.prepare(mongodConfig);
    _mongod = _mongodExe.start();

    _mongo = new MongoClient(mongodConfig.net().getBindIp(), mongodConfig.net().getPort());
  }

  @AfterAll
  protected static void tearDown() {
    _mongod.stop();
    _mongodExe.stop();
  }

  @BeforeEach
  public void before() {
    db.deleteAll();
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
    Set<String> expectedIds = cp.getPlayers().stream().map(player->player.getId()).collect(Collectors.toSet());
    Set<String> actualIds = fetched.getPlayers().stream().map(player->player.getId()).collect(Collectors.toSet());
    assertEquals(expectedIds, actualIds);
  }

  @Test
  public void updateTest() {
    // given
    ChineesPoepen cp = MockFactory.newChineesPoepen();

    ChineesPoepen saved = db.insertNew(cp);
    int newRound = 2;
    saved.addScores(getRoundScore(),newRound);
    // when
    ChineesPoepen updated = db.updateGame(saved);

    // then
    Optional<ChineesPoepen> optionalCp = db.getGame(updated.getId());
    assert optionalCp.isPresent();
    ChineesPoepen sut = optionalCp.get();
    assertEquals(newRound + 1, sut.getRound());
  }

  @Test
  public void updateTestCalledScore() {
    // given
    ChineesPoepen cp = MockFactory.newChineesPoepen();

    ChineesPoepen saved = db.insertNew(cp);
    int newRound = 2;
    saved.addScores(getRoundScoreCalled(), newRound);
    // when
    ChineesPoepen updated = db.updateGame(saved);

    // then
    Optional<ChineesPoepen> optionalCp = db.getGame(updated.getId());
    assert optionalCp.isPresent();
    ChineesPoepen sut = optionalCp.get();
    assertEquals(newRound + 1, sut.getRound());
    assertTrue(sut.getScores(newRound).stream().allMatch(score -> score.getPointsScored().isEmpty()));
  }

  @Test
  public void findByUserId() {
    // given
    ChineesPoepen cp = MockFactory.newChineesPoepen();

    // when
    db.insertNew(cp);
    db.insertNew(cp);
    db.insertNew(cp);

    // then
    List<ChineesPoepen> games = db.getGames(cp.getHost());
    assertEquals(3, games.size());
  }
}