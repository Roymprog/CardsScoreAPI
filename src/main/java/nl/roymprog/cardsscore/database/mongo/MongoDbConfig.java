package nl.roymprog.cardsscore.database.mongo;

import com.mongodb.MongoClientOptions;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import lombok.Getter;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.mongodb.WriteConcern.MAJORITY;

@Configuration
@Getter
public class MongoDbConfig {

  private final String host;
  private final int port;
  private final ServerAddress serverAddress;
  private final String database;
  private final String username;
  private final String password;
  private final MongoCredential credentials;
  private final MongoClientOptions.Builder mongoClientOptionsBuilder;

  public MongoDbConfig() {
    Config mongoConfig = ConfigFactory.load("application.conf")
            .getConfig("nl.roymprog.cardsscore")
            .getConfig("mongodb");
    host = mongoConfig.getString("host");

    if (mongoConfig.hasPath("port")) {
      port = mongoConfig.getInt("port");
      serverAddress = new ServerAddress(host, port);
    } else {
      port = ServerAddress.defaultPort();
      serverAddress = new ServerAddress(host);
    }

    database = mongoConfig.getString("database");
    username = mongoConfig.getString("username");
    password = mongoConfig.getString("password");
    credentials = MongoCredential.createCredential(username, database, password.toCharArray());

    mongoClientOptionsBuilder = MongoClientOptions
            .builder()
            .retryWrites(true)
            .writeConcern(MAJORITY);
  }
}