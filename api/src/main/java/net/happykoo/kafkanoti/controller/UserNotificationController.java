package net.happykoo.kafkanoti.controller;

import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import net.happykoo.kafkanoti.controller.dto.UserNotificationListResponse;
import net.happykoo.kafkanoti.service.UserNotificationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user-notifications")
@RequiredArgsConstructor
public class UserNotificationController {

  private final UserNotificationService userNotificationService;

  @GetMapping("/{userId}")
  public UserNotificationListResponse getNotifications(@PathVariable Long userId,
      @RequestParam(value = "pivot", required = false) LocalDateTime pivot) {
ㅇ
    return UserNotificationListResponse.of(
        userNotificationService.getUserNotificationByPivot(userId,
            pivot));
  }

}
