package net.happykoo.kafkanoti.domain.notification;

import java.time.LocalDateTime;
import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Document("notifications")
public class Notification {

  private String id;
  private Long userId;
  private NotificationType type;
  private LocalDateTime occurredAt; //실제 이벤트가 발생한 시간
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
  private LocalDateTime deletedAt;

  public Notification(String id,
      Long userId,
      NotificationType type,
      LocalDateTime occurredAt,
      LocalDateTime createdAt,
      LocalDateTime updatedAt,
      LocalDateTime deletedAt) {

    this.id = id;
    this.userId = userId;
    this.type = type;
    this.occurredAt = occurredAt;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
    this.deletedAt = deletedAt;
  }
}
