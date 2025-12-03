package net.happykoo.kafkanoti.task;

import lombok.RequiredArgsConstructor;
import net.happykoo.kafkanoti.domain.notification.NotificationType;
import net.happykoo.kafkanoti.event.FollowEvent;
import net.happykoo.kafkanoti.service.NotificationService;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UnFollowTask {

  private final NotificationService notificationService;

  public void processEvent(FollowEvent event) {
    notificationService.findByTypeAndUserIdAndTargetUserId(NotificationType.FOLLOW,
            event.getUserId(), event.getTargetUserId())
        .ifPresent(notification -> notificationService.deleteById(notification.getId()));
  }
}
