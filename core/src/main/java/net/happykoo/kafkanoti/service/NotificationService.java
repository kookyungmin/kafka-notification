package net.happykoo.kafkanoti.service;

import java.time.LocalDateTime;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import net.happykoo.kafkanoti.domain.notification.Notification;
import net.happykoo.kafkanoti.domain.notification.NotificationType;
import net.happykoo.kafkanoti.repository.NotificationRepository;
import net.happykoo.kafkanoti.service.dto.UserNotificationByPivotResult;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationService {

  private static final int PAGE_SIZE = 20;

  private final NotificationRepository notificationRepository;

  public UserNotificationByPivotResult findUserNotificationsByPivot(Long userId,
      LocalDateTime occurredAt) {
    Slice<Notification> result;
    if (occurredAt == null) {
      result = notificationRepository.findAllByUserIdOrderByOccurredAtDesc(userId,
          Pageable.ofSize(PAGE_SIZE));
    } else {
      result = notificationRepository.findAllByUserIdAndOccurredAtLessThanOrderByOccurredAtDesc(
          userId, occurredAt, Pageable.ofSize(PAGE_SIZE));
    }

    return new UserNotificationByPivotResult(
        result.toList(),
        result.hasNext()
    );
  }

  public void save(Notification notification) {
    notificationRepository.save(notification);
  }

  public Optional<Notification> findByTypeAndCommentId(NotificationType type, Long commentId) {
    return notificationRepository.findByTypeAndCommentId(type, commentId);
  }

  public Optional<Notification> findByTypeAndPostId(NotificationType type, Long postId) {
    return notificationRepository.findByTypeAndPostId(type, postId);
  }

  public Optional<Notification> findByTypeAndUserIdAndTargetUserId(NotificationType type,
      Long userId, Long targetUserId) {
    return notificationRepository.findByTypeAndUserIdAndTargetUserId(type, userId, targetUserId);
  }

  public void deleteById(String id) {
    notificationRepository.deleteById(id);
  }

  public LocalDateTime getLatestUpdatedAt(Long userId) {
    return notificationRepository.findFirstByUserIdOrderByLastUpdatedAtDesc(userId)
        .map(Notification::getLastUpdatedAt)
        .orElse(null);
  }
}
