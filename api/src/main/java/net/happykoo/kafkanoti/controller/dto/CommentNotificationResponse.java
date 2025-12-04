package net.happykoo.kafkanoti.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import lombok.Getter;
import net.happykoo.kafkanoti.domain.notification.NotificationType;
import net.happykoo.kafkanoti.service.dto.ConvertedCommentNotification;

@Getter
@Schema(description = "댓글 알림 응답")
public class CommentNotificationResponse extends UserNotificationResponse {

  @Schema(description = "댓글을 남긴 사용자 이름")
  private final String userName;
  @Schema(description = "댓글을 남긴 사용자 프로필 이미지 URL")
  private final String userProfileImageUrl;
  @Schema(description = "댓글 내용")
  private final String comment;
  @Schema(description = "게시물 이미지 URL")
  private final String postImageUrl;

  public CommentNotificationResponse(String id,
      NotificationType type,
      String userName,
      String userProfileImageUrl,
      String comment,
      String postImageUrl,
      LocalDateTime occurredAt) {
    super(id, type, occurredAt);
    this.userName = userName;
    this.userProfileImageUrl = userProfileImageUrl;
    this.comment = comment;
    this.postImageUrl = postImageUrl;
  }

  public static CommentNotificationResponse of(ConvertedCommentNotification notification) {
    return new CommentNotificationResponse(
        notification.getId(),
        notification.getType(),
        notification.getUserName(),
        notification.getUserProfileImageUrl(),
        notification.getComment(),
        notification.getPostImageUrl(),
        notification.getOccurredAt()
    );
  }
}
