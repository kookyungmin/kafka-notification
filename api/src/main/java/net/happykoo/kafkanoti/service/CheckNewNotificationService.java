package net.happykoo.kafkanoti.service;

import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CheckNewNotificationService {
  private final NotificationService notificationService;
  private final LastReadAtService lastReadAtService;

  public boolean checkNewNotification(Long userId) {
    LocalDateTime latestUpdatedAt = notificationService.getLatestUpdatedAt(userId);

    if (latestUpdatedAt == null) {
      return false;
    }

    LocalDateTime lastReadAt = lastReadAtService.findLastReadAt(userId);

    if (lastReadAt == null) {
      return true;
    }

    return lastReadAt.isBefore(latestUpdatedAt);
  }

}
