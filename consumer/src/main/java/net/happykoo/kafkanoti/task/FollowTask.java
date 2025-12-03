package net.happykoo.kafkanoti.task;

import java.time.LocalDateTime;
import java.time.ZoneId;
import lombok.RequiredArgsConstructor;
import net.happykoo.kafkanoti.domain.notification.FollowNotification;
import net.happykoo.kafkanoti.domain.notification.NotificationType;
import net.happykoo.kafkanoti.event.FollowEvent;
import net.happykoo.kafkanoti.service.NotificationService;
import net.happykoo.kafkanoti.utils.NotificationIdGenerator;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FollowTask {

  private final NotificationService notificationService;

  public void processEvent(FollowEvent event) {
    FollowNotification notification = createFollowNotification(event);

    notificationService.save(notification);
  }

  private FollowNotification createFollowNotification(FollowEvent event) {
    LocalDateTime now = LocalDateTime.now();
    return new FollowNotification(
        NotificationIdGenerator.generate(),
        event.getUserId(),
        event.getTargetUserId(),
        NotificationType.FOLLOW,
        LocalDateTime.ofInstant(event.getCreatedAt(), ZoneId.systemDefault()),
        now,
        now,
        now.plusDays(90)
    );
  }
}
