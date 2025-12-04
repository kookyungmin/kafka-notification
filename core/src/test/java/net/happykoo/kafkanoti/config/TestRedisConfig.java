package net.happykoo.kafkanoti.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.utility.DockerImageName;

@TestConfiguration
public class TestRedisConfig {

  @Value("${test-container.redis.image}")
  private String image;

  @Value("${test-container.redis.port}")
  private int containerPort;

  @Bean
  public GenericContainer getMongoInstance() {
    GenericContainer redis = new GenericContainer(DockerImageName.parse(image))
        .withExposedPorts(containerPort)
        .withReuse(false);
    redis.start();

    return redis;
  }

  @Bean(name = "redisConnectionFactory")
  public RedisConnectionFactory redisConnectionFactory(GenericContainer redis) {
    RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration(
        redis.getHost(), redis.getMappedPort(containerPort));
    return new LettuceConnectionFactory(redisStandaloneConfiguration);
  }
}
