package net.happykoo.kafkanoti.service;

import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import net.happykoo.kafkanoti.repository.NotificationReadRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LastReadAtService {

  private final NotificationReadRepository notificationReadRepository;

  public LocalDateTime saveLastReadAt(Long userId) {
    return notificationReadRepository.saveLastReadAt(userId);
  }

  public LocalDateTime findLastReadAt(Long userId) {
    return notificationReadRepository.findLastReadAt(userId);
  }

}
