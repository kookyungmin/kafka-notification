package net.happykoo.kafkanoti.service.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import net.happykoo.kafkanoti.domain.notification.NotificationType;

@Getter
@AllArgsConstructor
public class ConvertedNotification {

  protected String id;
  protected NotificationType type;
  protected Long userId;
  protected LocalDateTime occurredAt;
  protected LocalDateTime lastUpdatedAt;
}
