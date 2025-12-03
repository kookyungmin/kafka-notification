package net.happykoo.kafkanoti.controller.dto;

import java.time.LocalDateTime;
import java.util.List;
import net.happykoo.kafkanoti.service.dto.UserNotificationResult;

public record UserNotificationListResponse(
    List<UserNotificationResponse> notifications,
    LocalDateTime pivot,
    boolean hasNext
) {

  public static UserNotificationListResponse of(UserNotificationResult result) {
    List<UserNotificationResponse> notifications = result.notifications().stream()
        .map(UserNotificationResponse::of)
        .toList();

    LocalDateTime pivot = (result.hasNext() && !notifications.isEmpty())
        ? notifications.getLast().getOccurredAt() : null;

    return new UserNotificationListResponse(
        notifications,
        pivot,
        result.hasNext()
    );
  }
}
