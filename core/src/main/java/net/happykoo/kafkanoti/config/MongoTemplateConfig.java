package net.happykoo.kafkanoti.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Slf4j
@Configuration
@EnableMongoRepositories(
    basePackages = "net.happykoo.kafkanoti"
)
public class MongoTemplateConfig {

  @Bean(name = "mongoTemplate")
  public MongoTemplate mongoTemplate(MongoDatabaseFactory mongoDatabaseFactory,
      MongoConverter mongoConverter) {
    return new MongoTemplate(mongoDatabaseFactory, mongoConverter);

  }
}
