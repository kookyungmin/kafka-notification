package net.happykoo.kafkanoti.service.convertor;

import lombok.RequiredArgsConstructor;
import net.happykoo.kafkanoti.client.PostClient;
import net.happykoo.kafkanoti.client.UserClient;
import net.happykoo.kafkanoti.domain.notification.LikeNotification;
import net.happykoo.kafkanoti.domain.notification.Post;
import net.happykoo.kafkanoti.domain.user.User;
import net.happykoo.kafkanoti.service.dto.ConvertedLikeNotification;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LikeUserNotificationConvertor {

  private final UserClient userClient;
  private final PostClient postClient;

  public ConvertedLikeNotification convert(LikeNotification notification) {
    User user = userClient.getUser(notification.getLikerIds().get(0))
        .orElseThrow(IllegalArgumentException::new);
    Post post = postClient.getPost(notification.getPostId())
        .orElseThrow(IllegalArgumentException::new);

    return new ConvertedLikeNotification(
        notification.getId(),
        notification.getType(),
        notification.getUserId(),
        user.getName(),
        user.getProfileImageUrl(),
        notification.getLikerIds().size(),
        post.getImageUrl(),
        notification.getOccurredAt(),
        notification.getLastUpdatedAt()
    );
  }
}
