package net.happykoo.kafkanoti.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;

@Schema(description = "사용자가 알림 목록 읽은 시간 기록 후 응답")
public record LastReadAtResponse(
    @Schema(description = "사용자가 알림 목록 읽은 시간")
    LocalDateTime lastReadAt
) {

}
