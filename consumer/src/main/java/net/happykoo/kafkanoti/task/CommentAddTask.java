package net.happykoo.kafkanoti.task;

import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import net.happykoo.kafkanoti.client.CommentClient;
import net.happykoo.kafkanoti.client.PostClient;
import net.happykoo.kafkanoti.domain.notification.Comment;
import net.happykoo.kafkanoti.domain.notification.CommentNotification;
import net.happykoo.kafkanoti.domain.notification.Notification;
import net.happykoo.kafkanoti.domain.notification.Post;
import net.happykoo.kafkanoti.event.CommentEvent;
import net.happykoo.kafkanoti.service.NotificationService;
import net.happykoo.kafkanoti.utils.NotificationIdGenerator;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CommentAddTask {

  private final PostClient postClient;
  private final CommentClient commentClient;
  private final NotificationService notificationService;

  public void processEvent(CommentEvent event) {
    Post post = postClient.getPost(event.getPostId())
        .orElseThrow(IllegalArgumentException::new);
    Comment comment = commentClient.getComment(event.getCommentId())
        .orElseThrow(IllegalArgumentException::new);

    if (event.getUserId() == comment.getWriterId()) {
      return;
    }

    Notification notification = createNotification(post, comment);
    notificationService.save(notification);
  }

  private Notification createNotification(Post post, Comment comment) {
    LocalDateTime now = LocalDateTime.now();

    return new CommentNotification(
        NotificationIdGenerator.generate(),
        post.getWriterId(),
        post.getId(),
        comment.getId(),
        comment.getWriterId(),
        comment.getContent(),
        comment.getCreatedAt(),
        now,
        now,
        now.plusDays(90)
    );
  }
}
