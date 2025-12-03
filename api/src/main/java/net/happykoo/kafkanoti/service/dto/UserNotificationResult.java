package net.happykoo.kafkanoti.service.dto;

import java.util.List;

public record UserNotificationResult(
    List<ConvertedNotification> notifications,
    boolean hasNext
) {

}
