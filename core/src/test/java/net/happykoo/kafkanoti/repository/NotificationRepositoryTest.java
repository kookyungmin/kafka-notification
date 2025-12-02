package net.happykoo.kafkanoti.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.Optional;
import net.happykoo.kafkanoti.config.TestMongoConfig;
import net.happykoo.kafkanoti.domain.notification.Notification;
import net.happykoo.kafkanoti.domain.notification.NotificationType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootApplication
@SpringBootTest(classes = TestMongoConfig.class)
class NotificationRepositoryTest {

  @Autowired
  private NotificationRepository sut;
  private Notification notification;

  @BeforeEach
  void setup() {
    notification = new Notification("1",
        2L,
        NotificationType.LIKE,
        LocalDateTime.now(),
        LocalDateTime.now(),
        LocalDateTime.now(),
        LocalDateTime.now().plusDays(90));
  }

  @Test
  void test_save() {
    sut.save(notification);
    Optional<Notification> opt = sut.findById("1");
    assertAll(() -> assertTrue(opt.isPresent()),
        () -> assertThat(opt.get().getUserId()).isEqualTo(notification.getUserId()),
        () -> assertThat(opt.get().getType()).isEqualTo(notification.getType()));
  }

  @Test
  void test_deleteById() {
    sut.save(notification);

    sut.deleteById("1");

    Optional<Notification> opt = sut.findById("1");
    assertAll(() -> assertFalse(opt.isPresent()));
  }

}