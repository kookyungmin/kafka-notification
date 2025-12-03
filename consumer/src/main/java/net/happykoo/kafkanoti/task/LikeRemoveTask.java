package net.happykoo.kafkanoti.task;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import net.happykoo.kafkanoti.domain.notification.LikeNotification;
import net.happykoo.kafkanoti.domain.notification.Notification;
import net.happykoo.kafkanoti.domain.notification.NotificationType;
import net.happykoo.kafkanoti.event.LikeEvent;
import net.happykoo.kafkanoti.service.NotificationService;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LikeRemoveTask {

  private final NotificationService notificationService;

  public void processEvent(LikeEvent event) {
    Optional<Notification> notificationOpt = notificationService.findByTypeAndPostId(
        NotificationType.LIKE,
        event.getPostId());

    if (notificationOpt.isPresent()) {
      LikeNotification notification = (LikeNotification) notificationOpt.get();
      notification.removeLiker(event.getUserId());

      if (notification.getLikerIds().isEmpty()) {
        notificationService.deleteById(notification.getId());
      } else {
        notificationService.save(notification);
      }
    }
  }
}
