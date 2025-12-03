package net.happykoo.kafkanoti.controller.dto;

import java.time.LocalDateTime;
import lombok.Getter;
import net.happykoo.kafkanoti.domain.notification.NotificationType;
import net.happykoo.kafkanoti.service.dto.ConvertedCommentNotification;

@Getter
public class CommentNotificationResponse extends UserNotificationResponse {

  private final String userName;
  private final String userProfileImageUrl;
  private final String comment;
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
