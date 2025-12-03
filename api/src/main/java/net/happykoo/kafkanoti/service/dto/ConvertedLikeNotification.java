package net.happykoo.kafkanoti.service.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import net.happykoo.kafkanoti.domain.notification.NotificationType;

@Getter
public class ConvertedLikeNotification extends ConvertedNotification {

  private final String userName;
  private final String userProfileImageUrl;
  private final int userCount;
  private final String postImageUrl;

  public ConvertedLikeNotification(String id,
      NotificationType type,
      Long userId,
      String userName,
      String userProfileImageUrl,
      int userCount,
      String postImageUrl,
      LocalDateTime occurredAt,
      LocalDateTime lastUpdatedAt) {
    super(id, type, userId, occurredAt, lastUpdatedAt);
    this.userName = userName;
    this.userProfileImageUrl = userProfileImageUrl;
    this.userCount = userCount;
    this.postImageUrl = postImageUrl;
  }
}
