package net.happykoo.kafkanoti.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import lombok.Getter;
import net.happykoo.kafkanoti.domain.notification.NotificationType;
import net.happykoo.kafkanoti.service.dto.ConvertedLikeNotification;

@Schema(description = "좋아요 알림 응답")
@Getter
public class LikeNotificationResponse extends UserNotificationResponse {

  @Schema(description = "좋아요한 사용자 이름")
  private final String userName;
  @Schema(description = "좋아요한 사용자 프로필 이미지 URL")
  private final String userProfileImageUrl;
  @Schema(description = "좋아요한 사용자 수")
  private final int userCount;
  @Schema(description = "좋아요한 게시물 이미지 URL")
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
