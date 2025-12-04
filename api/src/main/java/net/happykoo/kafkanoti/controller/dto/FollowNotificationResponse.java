package net.happykoo.kafkanoti.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import lombok.Getter;
import net.happykoo.kafkanoti.domain.notification.NotificationType;
import net.happykoo.kafkanoti.service.dto.ConvertedFollowNotification;

@Getter
@Schema(description = "Follow 알림 응답")
public class FollowNotificationResponse extends UserNotificationResponse {

  @Schema(name = "팔로우한 사용자 이름")
  private final String userName;
  @Schema(name = "팔로우한 사용자 이미지 URL")
  private final String userProfileImageUrl;
  @Schema(name = "팔로우한 사용자 맞팔로우 여부")
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
