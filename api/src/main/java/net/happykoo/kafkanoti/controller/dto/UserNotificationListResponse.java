package net.happykoo.kafkanoti.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import java.util.List;
import net.happykoo.kafkanoti.service.dto.UserNotificationResult;

@Schema(description = "유저 알림 목록 응답")
public record UserNotificationListResponse(
    @Schema(description = "알림 목록")
    List<UserNotificationResponse> notifications,

    @Schema(description = "다음 페이지 요청 시 전달할 pivot 파라미터")
    LocalDateTime pivot,

    @Schema(description = "다음 페이지 존재 여부")
    boolean hasNext
) {

  public static UserNotificationListResponse of(UserNotificationResult result) {
    List<UserNotificationResponse> notifications = result.notifications().stream()
        .map(UserNotificationResponse::of)
        .toList();

    LocalDateTime pivot = (result.hasNext() && !notifications.isEmpty())
        ? notifications.get(notifications.size() - 1).getOccurredAt() : null;

    return new UserNotificationListResponse(
        notifications,
        pivot,
        result.hasNext()
    );
  }
}
