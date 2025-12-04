package net.happykoo.kafkanoti.controller.dto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "유저 알림 응답")
@JsonSubTypes({
    @JsonSubTypes.Type(value = CommentNotificationResponse.class),
    @JsonSubTypes.Type(value = LikeNotificationResponse.class),
    @JsonSubTypes.Type(value = CommentNotificationResponse.class)
})
public class UserNotificationResponse {

  @Schema(description = "알림 ID")
  private String id;
  @Schema(description = "알림 Type")
  private NotificationType type;
  @Schema(description = "알림 이벤트 발생 시간")
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

