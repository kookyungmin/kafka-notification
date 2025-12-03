package net.happykoo.kafkanoti.task;

import lombok.RequiredArgsConstructor;
import net.happykoo.kafkanoti.domain.notification.LikeNotification;
import net.happykoo.kafkanoti.domain.notification.NotificationType;
import net.happykoo.kafkanoti.event.LikeEvent;
import net.happykoo.kafkanoti.service.NotificationService;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LikeRemoveTask {

  private final NotificationService notificationService;

  public void processEvent(LikeEvent event) {
    LikeNotification notification = (LikeNotification) notificationService.findByTypeAndPostId(
        NotificationType.LIKE,
        event.getPostId()).orElseThrow(IllegalArgumentException::new);

    notification.removeLiker(event.getUserId());

    if (notification.getLikerIds().isEmpty()) {
      notificationService.deleteById(notification.getId());
    } else {
      notificationService.save(notification);
    }
  }
}
