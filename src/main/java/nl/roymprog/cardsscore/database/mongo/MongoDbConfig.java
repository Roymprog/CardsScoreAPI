package nl.roymprog.cardsscore.database.mongo;

import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import lombok.Getter;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Configuration
@Getter
public class MongoDbConfig {

  private final String host;
  private final int port;
  private final ServerAddress serverAddress;
  private final String database;
  private final Optional<MongoCredential> credentials;

  public MongoDbConfig() {
    Config mongoConfig = ConfigFactory.load("application.conf")
            .getConfig("nl.roymprog.cardsscore")
            .getConfig("mongodb");
    host = mongoConfig.getString("host");
    port = mongoConfig.getInt("port");
    serverAddress = new ServerAddress(host, port);
    database = mongoConfig.getString("database");
    if (mongoConfig.hasPath("username") && mongoConfig.hasPath("password")) {
      String username = mongoConfig.getString("username");
      String password = mongoConfig.getString("password");
      credentials = Optional.of(MongoCredential.createCredential(username, database, password.toCharArray()));
    } else {
      credentials = Optional.empty();
    }
  }

}