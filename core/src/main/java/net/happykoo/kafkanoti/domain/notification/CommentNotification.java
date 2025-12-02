package net.happykoo.kafkanoti.domain.notification;

import java.time.LocalDateTime;
import lombok.Getter;
import org.springframework.data.annotation.TypeAlias;

@Getter
@TypeAlias("CommentNotification")
public class CommentNotification extends Notification {

  private final Long postId;
  private final Long commentId;
  private final Long writerId;
  private final String comment;


  public CommentNotification(String id,
      Long userId,
      Long postId,
      Long commentId,
      Long writerId,
      String comment,
      LocalDateTime occurredAt,
      LocalDateTime createdAt,
      LocalDateTime updatedAt,
      LocalDateTime deletedAt) {
    super(id, userId, NotificationType.COMMENT, occurredAt, createdAt, updatedAt, deletedAt);
    this.postId = postId;
    this.writerId = writerId;
    this.commentId = commentId;
    this.comment = comment;
  }
}
