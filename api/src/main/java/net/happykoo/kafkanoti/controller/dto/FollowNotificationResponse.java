package net.happykoo.kafkanoti.controller.dto;

import java.time.LocalDateTime;
import lombok.Getter;
import net.happykoo.kafkanoti.domain.notification.NotificationType;
import net.happykoo.kafkanoti.service.dto.ConvertedFollowNotification;

@Getter
public class FollowNotificationResponse extends UserNotificationResponse {

  private final String userName;
  private final String userProfileImageUrl;
  private final boolean isFollowing;

  public FollowNotificationResponse(String id,
      NotificationType type,
      String userName,
      String userProfileImageUrl,
      boolean isFollowing,
      LocalDateTime occurredAt) {
    super(id, type, occurredAt);
    this.userName = userName;
    this.userProfileImageUrl = userProfileImageUrl;
    this.isFollowing = isFollowing;
  }

  public static FollowNotificationResponse of(ConvertedFollowNotification notification) {
    return new FollowNotificationResponse(
        notification.getId(),
        notification.getType(),
        notification.getUserName(),
        notification.getUserProfileImageUrl(),
        notification.isFollowing(),
        notification.getOccurredAt()
    );
  }
}
