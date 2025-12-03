package net.happykoo.kafkanoti.service;

import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import net.happykoo.kafkanoti.domain.notification.CommentNotification;
import net.happykoo.kafkanoti.domain.notification.FollowNotification;
import net.happykoo.kafkanoti.domain.notification.LikeNotification;
import net.happykoo.kafkanoti.service.convertor.CommentUserNotificationConvertor;
import net.happykoo.kafkanoti.service.convertor.FollowUserNotificationConvertor;
import net.happykoo.kafkanoti.service.convertor.LikeUserNotificationConvertor;
import net.happykoo.kafkanoti.service.dto.ConvertedNotification;
import net.happykoo.kafkanoti.service.dto.UserNotificationByPivotResult;
import net.happykoo.kafkanoti.service.dto.UserNotificationResult;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserNotificationService {

  private final CommentUserNotificationConvertor commentConvertor;
  private final LikeUserNotificationConvertor likeConvertor;
  private final FollowUserNotificationConvertor followConvertor;

  private final NotificationService notificationService;

  public UserNotificationResult getUserNotificationByPivot(Long userId, LocalDateTime pivot) {
    UserNotificationByPivotResult result = notificationService.findUserNotificationsByPivot(userId,
        pivot);

    List<ConvertedNotification> convertedNotifications = result.notifications()
        .stream()
        .map(notification -> switch (notification.getType()) {
          case COMMENT -> commentConvertor.convert((CommentNotification) notification);
          case LIKE -> likeConvertor.convert((LikeNotification) notification);
          case FOLLOW -> followConvertor.convert((FollowNotification) notification);
        })
        .toList();

    return new UserNotificationResult(
        convertedNotifications,
        result.hasNext()
    );
  }

}
