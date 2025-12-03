package net.happykoo.kafkanoti.controller.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import net.happykoo.kafkanoti.domain.notification.NotificationType;
import net.happykoo.kafkanoti.service.dto.ConvertedCommentNotification;
import net.happykoo.kafkanoti.service.dto.ConvertedFollowNotification;
import net.happykoo.kafkanoti.service.dto.ConvertedLikeNotification;
import net.happykoo.kafkanoti.service.dto.ConvertedNotification;

@AllArgsConstructor
@Getter
public class UserNotificationResponse {

  private String id;
  private NotificationType type;
  private LocalDateTime occurredAt;

  public static UserNotificationResponse of(ConvertedNotification notification) {
    switch (notification.getType()) {
      case COMMENT -> {
        return CommentNotificationResponse.of((ConvertedCommentNotification) notification);
      }
      case LIKE -> {
        return LikeNotificationResponse.of((ConvertedLikeNotification) notification);
      }
      case FOLLOW -> {
        return FollowNotificationResponse.of((ConvertedFollowNotification) notification);
      }
      default -> throw new IllegalArgumentException(
          "Unsupported notification type : " + notification.getType());
    }
  }
}

