package net.happykoo.kafkanoti.service.dto;

import java.util.List;
import net.happykoo.kafkanoti.domain.notification.Notification;

public record UserNotificationByPivotResult(
    List<Notification> notifications,
    boolean hasNext
) {

}
