package net.happykoo.kafkanoti.task;

import lombok.RequiredArgsConstructor;
import net.happykoo.kafkanoti.domain.notification.NotificationType;
import net.happykoo.kafkanoti.event.CommentEvent;
import net.happykoo.kafkanoti.service.NotificationService;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CommentRemoveTask {

  private final NotificationService notificationService;

  public void processEvent(CommentEvent event) {
    notificationService.findByTypeAndCommentId(NotificationType.COMMENT, event.getCommentId())
        .ifPresentOrElse(
            (notification) -> notificationService.deleteById(notification.getId()),
            IllegalArgumentException::new
        );
  }
}
