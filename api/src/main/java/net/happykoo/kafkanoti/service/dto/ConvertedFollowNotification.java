package net.happykoo.kafkanoti.service.dto;

import java.time.LocalDateTime;
import lombok.Getter;
import net.happykoo.kafkanoti.domain.notification.NotificationType;

@Getter
public class ConvertedFollowNotification extends ConvertedNotification {

  private final String userName;
  private final String userProfileImageUrl;
  private final boolean isFollowing;

  public ConvertedFollowNotification(String id,
      NotificationType type,
      Long userId,
      String userName,
      String userProfileImageUrl,
      boolean isFollowing,
      LocalDateTime occurredAt,
      LocalDateTime lastUpdatedAt) {
    super(id, type, userId, occurredAt, lastUpdatedAt);
    this.userName = userName;
    this.userProfileImageUrl = userProfileImageUrl;
    this.isFollowing = isFollowing;
  }
}
