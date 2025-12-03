package net.happykoo.kafkanoti.controller.dto;

import java.time.LocalDateTime;
import lombok.Getter;
import net.happykoo.kafkanoti.domain.notification.NotificationType;
import net.happykoo.kafkanoti.service.dto.ConvertedLikeNotification;

@Getter
public class LikeNotificationResponse extends UserNotificationResponse {

  private final String userName;
  private final String userProfileImageUrl;
  private final int userCount;
  private final String postImageUrl;

  public LikeNotificationResponse(String id,
      NotificationType type,
      String userName,
      String userProfileImageUrl,
      int userCount,
      String postImageUrl,
      LocalDateTime occurredAt) {
    super(id, type, occurredAt);
    this.userName = userName;
    this.userProfileImageUrl = userProfileImageUrl;
    this.userCount = userCount;
    this.postImageUrl = postImageUrl;
  }

  public static LikeNotificationResponse of(ConvertedLikeNotification notification) {
    return new LikeNotificationResponse(
        notification.getId(),
        notification.getType(),
        notification.getUserName(),
        notification.getUserProfileImageUrl(),
        notification.getUserCount(),
        notification.getPostImageUrl(),
        notification.getOccurredAt()
    );
  }
}
