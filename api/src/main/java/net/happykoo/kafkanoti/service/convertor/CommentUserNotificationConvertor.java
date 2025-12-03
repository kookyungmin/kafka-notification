package net.happykoo.kafkanoti.service.convertor;

import lombok.RequiredArgsConstructor;
import net.happykoo.kafkanoti.client.PostClient;
import net.happykoo.kafkanoti.client.UserClient;
import net.happykoo.kafkanoti.domain.notification.CommentNotification;
import net.happykoo.kafkanoti.domain.notification.Post;
import net.happykoo.kafkanoti.domain.user.User;
import net.happykoo.kafkanoti.service.dto.ConvertedCommentNotification;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CommentUserNotificationConvertor {

  private final UserClient userClient;
  private final PostClient postClient;

  public ConvertedCommentNotification convert(CommentNotification notification) {
    User user = userClient.getUser(notification.getWriterId())
        .orElseThrow(IllegalArgumentException::new);
    Post post = postClient.getPost(notification.getPostId())
        .orElseThrow(IllegalArgumentException::new);

    return new ConvertedCommentNotification(
        notification.getId(),
        notification.getType(),
        notification.getUserId(),
        user.getName(),
        user.getProfileImageUrl(),
        notification.getComment(),
        post.getImageUrl(),
        notification.getOccurredAt(),
        notification.getLastUpdatedAt()
    );
  }
}
