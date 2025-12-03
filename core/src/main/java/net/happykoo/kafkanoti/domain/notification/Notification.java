package net.happykoo.kafkanoti.domain.notification;

import java.time.LocalDateTime;
import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

@Getter
@Document("notifications")
public class Notification {

  @Field(targetType = FieldType.STRING)
  private String id;
  private Long userId; // 행위자 ID
  private NotificationType type;
  private LocalDateTime occurredAt; //실제 이벤트가 발생한 시간
  private LocalDateTime createdAt;
  private LocalDateTime lastUpdatedAt;
  private LocalDateTime deletedAt;

  public Notification(String id,
      Long userId,
      NotificationType type,
      LocalDateTime occurredAt,
      LocalDateTime createdAt,
      LocalDateTime lastUpdatedAt,
      LocalDateTime deletedAt) {

    this.id = id;
    this.userId = userId;
    this.type = type;
    this.occurredAt = occurredAt;
    this.createdAt = createdAt;
    this.lastUpdatedAt = lastUpdatedAt;
    this.deletedAt = deletedAt;
  }

  public void updateOccurredAt(LocalDateTime occurredAt) {
    this.occurredAt = occurredAt;
  }

  public void updateUpdatedAt() {
    LocalDateTime now = LocalDateTime.now();
    this.lastUpdatedAt = now;
    this.deletedAt = now.plusDays(90);
  }
}
