package net.happykoo.kafkanoti.service.convertor;

import lombok.RequiredArgsConstructor;
import net.happykoo.kafkanoti.client.UserClient;
import net.happykoo.kafkanoti.domain.notification.FollowNotification;
import net.happykoo.kafkanoti.domain.user.User;
import net.happykoo.kafkanoti.service.dto.ConvertedFollowNotification;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FollowUserNotificationConvertor {

  private final UserClient userClient;

  public ConvertedFollowNotification convert(FollowNotification notification) {
    User user = userClient.getUser(notification.getUserId())
        .orElseThrow(IllegalArgumentException::new);
    boolean isFollowing = userClient.isFollowing(notification.getTargetUserId(),
        notification.getUserId());

    return new ConvertedFollowNotification(
        notification.getId(),
        notification.getType(),
        notification.getUserId(),
        user.getName(),
        user.getProfileImageUrl(),
        isFollowing,
        notification.getOccurredAt(),
        notification.getLastUpdatedAt()
    );
  }
}
