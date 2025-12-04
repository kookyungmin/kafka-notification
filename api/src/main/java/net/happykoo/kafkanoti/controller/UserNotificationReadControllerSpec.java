package net.happykoo.kafkanoti.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.happykoo.kafkanoti.controller.dto.LastReadAtResponse;

@Tag(name = "사용자 알림센터 API")
public interface UserNotificationReadControllerSpec {
  @Operation(summary = "사용자 알림 목록 읽은 시간 기록")
  LastReadAtResponse saveLastAtResponse(@Parameter(example = "1") Long userId);

}
