package net.happykoo.kafkanoti.controller;

import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import net.happykoo.kafkanoti.controller.dto.UserNotificationListResponse;
import net.happykoo.kafkanoti.service.UserNotificationService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user-notifications")
@RequiredArgsConstructor
public class UserNotificationController implements UserNotificationControllerSpec {

  private final UserNotificationService userNotificationService;

  @GetMapping("/{userId}")
  @Override
  public UserNotificationListResponse getNotifications(@PathVariable Long userId,
      @RequestParam(value = "pivot", required = false) @DateTimeFormat(iso = ISO.DATE_TIME) LocalDateTime pivot) {

    return UserNotificationListResponse.of(
        userNotificationService.getUserNotificationByPivot(userId,
            pivot));
  }

}
