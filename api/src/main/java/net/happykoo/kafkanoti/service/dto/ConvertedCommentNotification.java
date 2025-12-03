package net.happykoo.kafkanoti.service.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import net.happykoo.kafkanoti.domain.notification.NotificationType;

@Getter
public class ConvertedCommentNotification extends ConvertedNotification {

  private final String userName;
  private final String userProfileImageUrl;
  private final String comment;
  private final String postImageUrl;

  public ConvertedCommentNotification(String id,
      NotificationType type,
      Long userId,
      String userName,
      String userProfileImageUrl,
      String comment,
      String postImageUrl,
      LocalDateTime occurredAt,
      LocalDateTime lastUpdatedAt) {
    super(id, type, userId, occurredAt, lastUpdatedAt);
    this.userName = userName;
    this.userProfileImageUrl = userProfileImageUrl;
    this.comment = comment;
    this.postImageUrl = postImageUrl;
  }
}
