package net.happykoo.kafkanoti.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.time.LocalDateTime;
import net.happykoo.kafkanoti.controller.dto.UserNotificationListResponse;

@Tag(name = "사용자 알림센터 API")
public interface UserNotificationControllerSpec {

  @Operation(summary = "사용자 알림목록 조회")
  UserNotificationListResponse getNotifications(
      @Parameter(example = "1") Long userId,
      @Parameter(example = "2025-12-04T11:37:00.382Z") LocalDateTime pivot
  );

}
