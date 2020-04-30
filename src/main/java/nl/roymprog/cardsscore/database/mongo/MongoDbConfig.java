package nl.roymprog.cardsscore.database.mongo;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import lombok.Getter;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class MongoDbConfig {

  private final String host;
  private final int port;
  private final String database;

  public MongoDbConfig() {
    Config mongoConfig = ConfigFactory.load()
            .getConfig("nl.roymprog.cardsscore")
            .getConfig("mongodb");
    host = mongoConfig.getString("host");
    port = mongoConfig.getInt("port");
    database = mongoConfig.getString("database");
  }

}