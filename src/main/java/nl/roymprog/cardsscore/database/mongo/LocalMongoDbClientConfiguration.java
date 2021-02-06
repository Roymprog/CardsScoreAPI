package nl.roymprog.cardsscore.database.mongo;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories
@Profile("local")
class LocalMongoDbClientConfiguration extends AbstractMongoConfiguration {

  @Autowired
  private MongoDbConfig mongoDbConfig;

  @Override
  protected String getDatabaseName() {
    return mongoDbConfig.getDatabase();
  }

  @Override
  public MongoClient mongoClient() {
    return new MongoClient(mongoDbConfig.getServerAddress(),
                           mongoDbConfig.getCredentials(),
                           MongoClientOptions.builder().build());
  }
}