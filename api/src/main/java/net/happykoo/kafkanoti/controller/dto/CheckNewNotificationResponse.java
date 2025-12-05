package net.happykoo.kafkanoti.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "사용자 신규 알림 조회 응답")
public record CheckNewNotificationResponse(
    @Schema(description = "신규 알림 존재 여부")
    boolean hasNew
) {

}
