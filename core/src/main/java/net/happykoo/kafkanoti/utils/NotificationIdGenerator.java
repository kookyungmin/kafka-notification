package net.happykoo.kafkanoti.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class NotificationIdGenerator {

  public static String generate() {
    return new ObjectId().toString();
  }
}
