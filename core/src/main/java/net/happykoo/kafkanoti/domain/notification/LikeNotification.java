package net.happykoo.kafkanoti.domain.notification;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;
import org.springframework.data.annotation.TypeAlias;

@TypeAlias("LikeNotification")
@Getter
public class LikeNotification extends Notification {

  private final Long postId;
  private final List<Long> likerIds;

  public LikeNotification(String id,
      Long userId,
      NotificationType type,
      LocalDateTime occurredAt,
      LocalDateTime createdAt,
      LocalDateTime lastUpdatedAt,
      LocalDateTime deletedAt,
      Long postId,
      List<Long> likerIds) {
    super(id, userId, type, occurredAt, createdAt, lastUpdatedAt, deletedAt);
    this.postId = postId;
    this.likerIds = likerIds;
  }

  public void addLiker(Long likerId, LocalDateTime occurredAt) {
    this.likerIds.add(likerId);
    this.updateOccurredAt(occurredAt);
    this.updateUpdatedAt();
  }

  public void removeLiker(Long likerId) {
    this.likerIds.remove(likerId);
    this.updateUpdatedAt();
  }
}
