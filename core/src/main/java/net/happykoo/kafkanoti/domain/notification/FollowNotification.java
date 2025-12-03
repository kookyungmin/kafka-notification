package net.happykoo.kafkanoti.domain.notification;

import java.time.LocalDateTime;
import org.springframework.data.annotation.TypeAlias;

@TypeAlias("FollowNotification")
public class FollowNotification extends Notification {

  private final Long targetUserId;

  public FollowNotification(String id,
      Long userId,
      Long targetUserId,
      NotificationType type,
      LocalDateTime occurredAt,
      LocalDateTime createdAt,
      LocalDateTime lastUpdatedAt,
      LocalDateTime deletedAt) {
    super(id, userId, type, occurredAt, createdAt, lastUpdatedAt, deletedAt);
    this.targetUserId = targetUserId;
  }
}
