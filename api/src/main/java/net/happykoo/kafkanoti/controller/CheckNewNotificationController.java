package net.happykoo.kafkanoti.controller;

import lombok.RequiredArgsConstructor;
import net.happykoo.kafkanoti.controller.dto.CheckNewNotificationResponse;
import net.happykoo.kafkanoti.service.CheckNewNotificationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user-notifications")
@RequiredArgsConstructor
public class CheckNewNotificationController implements CheckNewNotificationControllerSpec {
  private final CheckNewNotificationService checkNewNotificationService;

  @Override
  @GetMapping("/{userId}/new")
  public CheckNewNotificationResponse checkNewNotification(@PathVariable Long userId) {
    boolean hasNewNotification = checkNewNotificationService.checkNewNotification(userId);

    return new CheckNewNotificationResponse(hasNewNotification);
  }

}
