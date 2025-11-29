package net.happykoo.kafkanoti.config;

import com.mongodb.ConnectionString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.utility.DockerImageName;

@TestConfiguration
public class TestMongoConfig {

  @Value("${test-container.mongodb.image}")
  private String image;

  @Value("${test-container.mongodb.port}")
  private int containerPort;

  @Value("${test-container.mongodb.dbname}")
  private String dbName;

  @Bean
  public GenericContainer getMongoInstance() {
    GenericContainer mongo = new GenericContainer(DockerImageName.parse(image))
        .withExposedPorts(containerPort)
        .withReuse(false);
    mongo.start();

    return mongo;
  }

  @Bean(name = "mongoDatabaseFactory")
  public MongoDatabaseFactory notificationMongoFactory(GenericContainer mongo) {
    return new SimpleMongoClientDatabaseFactory(connectionString(mongo));
  }

  private ConnectionString connectionString(GenericContainer mongo) {
    StringBuilder sb = new StringBuilder();

    sb.append("mongodb://");
    sb.append(mongo.getHost());
    sb.append(":");
    sb.append(mongo.getMappedPort(containerPort));
    sb.append("/");
    sb.append(dbName);

    return new ConnectionString(sb.toString());
  }
}
