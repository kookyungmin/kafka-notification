package net.happykoo.kafkanoti.task;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import net.happykoo.kafkanoti.client.PostClient;
import net.happykoo.kafkanoti.domain.notification.LikeNotification;
import net.happykoo.kafkanoti.domain.notification.Notification;
import net.happykoo.kafkanoti.domain.notification.NotificationType;
import net.happykoo.kafkanoti.domain.notification.Post;
import net.happykoo.kafkanoti.event.LikeEvent;
import net.happykoo.kafkanoti.service.NotificationService;
import net.happykoo.kafkanoti.utils.NotificationIdGenerator;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LikeAddTask {

  private final PostClient postClient;
  private final NotificationService notificationService;

  public void processEvent(LikeEvent event) {
    Post post = postClient.getPost(event.getPostId())
        .orElseThrow(IllegalArgumentException::new);

    if (post.getWriterId() == event.getUserId()) {
      return;
    }

    notificationService.save(createOrUpdateNotification(post, event));
  }

  private Notification createOrUpdateNotification(Post post, LikeEvent event) {
    Optional<Notification> notificationOpt = notificationService.findByTypeAndPostId(
        NotificationType.LIKE, post.getId());

    if (notificationOpt.isPresent()) {
      return updateNotification((LikeNotification) notificationOpt.get(), event);
    } else {
      return createNotification(post, event);
    }
  }

  private Notification updateNotification(LikeNotification notification, LikeEvent event) {
    notification.addLiker(event.getUserId(),
        LocalDateTime.ofInstant(event.getCreatedAt(), ZoneId.systemDefault()));
    return notification;
  }

  private Notification createNotification(Post post, LikeEvent event) {
    LocalDateTime now = LocalDateTime.now();
    return new LikeNotification(
        NotificationIdGenerator.generate(),
        post.getWriterId(),
        NotificationType.LIKE,
        LocalDateTime.ofInstant(event.getCreatedAt(), ZoneId.systemDefault()),
        now,
        now,
        now.plusDays(90),
        post.getId(),
        List.of(event.getUserId())
    );
  }
}
