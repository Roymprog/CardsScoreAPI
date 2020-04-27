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
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.io.IOException;

@Configuration
@EnableMongoRepositories
public class MongoItTestConfiguration extends AbstractMongoConfiguration {

    @Override
    public MongoClient mongoClient() {
        MongodStarter starter = MongodStarter.getDefaultInstance();

        String bindIp = "localhost";
        int port = 12345;
        IMongodConfig mongodConfig = null;
        try {
            mongodConfig = new MongodConfigBuilder()
                    .version(Version.Main.PRODUCTION)
                    .net(new Net(bindIp, port, Network.localhostIsIPv6()))
                    .build();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        MongodExecutable mongodExecutable = null;
        try {
            mongodExecutable = starter.prepare(mongodConfig);
            mongodExecutable.start();

            return new MongoClient(mongodConfig.net().getBindIp(), mongodConfig.net().getPort());

        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (mongodExecutable != null)
                mongodExecutable.stop();
        }
    }

    @Override
    protected String getDatabaseName() {
        return "test";
    }

}
