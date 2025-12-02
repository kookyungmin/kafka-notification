package net.happykoo.kafkanoti.service;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import net.happykoo.kafkanoti.domain.notification.Notification;
import net.happykoo.kafkanoti.domain.notification.NotificationType;
import net.happykoo.kafkanoti.repository.NotificationRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationService {

  private final NotificationRepository notificationRepository;

  public void save(Notification notification) {
    notificationRepository.save(notification);
  }

  public Optional<Notification> findByTypeAndCommentId(NotificationType type, Long commentId) {
    return notificationRepository.findByTypeAndCommentId(type, commentId);
  }

  public void deleteById(String id) {
    notificationRepository.deleteById(id);

  }
}
