package net.happykoo.kafkanoti.repository;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class NotificationReadRepository {

  private final RedisTemplate<String, String> redisTemplate;

  public LocalDateTime saveLastReadAt(Long userId) {
    LocalDateTime now = LocalDateTime.now();
    long lastReadAt = now.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    String key = getLastReadAtKey(userId);

    redisTemplate.opsForValue().set(key, String.valueOf(lastReadAt));
    redisTemplate.expire(key, 90, TimeUnit.DAYS);

    return now;
  }

  public LocalDateTime findLastReadAt(Long userId) {
    String value = redisTemplate.opsForValue().get(getLastReadAtKey(userId));
    if (value == null) {
      return null;
    }
    return LocalDateTime.ofInstant(Instant.ofEpochMilli(Long.parseLong(value)), ZoneId.systemDefault());
  }

  private String getLastReadAtKey(Long userId) {
    StringBuilder sb = new StringBuilder();

    sb.append(userId);
    sb.append(":lastReadAt");

    return sb.toString();
  }

}
