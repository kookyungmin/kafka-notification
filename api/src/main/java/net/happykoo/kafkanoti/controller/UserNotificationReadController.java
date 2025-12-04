package net.happykoo.kafkanoti.controller;

import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import net.happykoo.kafkanoti.controller.dto.LastReadAtResponse;
import net.happykoo.kafkanoti.service.LastReadAtService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user-notifications")
@RequiredArgsConstructor
public class UserNotificationReadController implements UserNotificationReadControllerSpec {
  private final LastReadAtService lastReadAtService;

  @Override
  @PutMapping("/{userId}/read")
  public LastReadAtResponse saveLastAtResponse(@PathVariable Long userId) {
    LocalDateTime lastReadAt = lastReadAtService.saveLastReadAt(userId);

    return new LastReadAtResponse(lastReadAt);
  }
}
