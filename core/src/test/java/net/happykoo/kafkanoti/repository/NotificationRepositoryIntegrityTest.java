package net.happykoo.kafkanoti.repository;

import static net.happykoo.kafkanoti.domain.notification.NotificationType.COMMENT;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.Optional;
import net.happykoo.kafkanoti.config.TestMongoConfig;
import net.happykoo.kafkanoti.domain.notification.CommentNotification;
import net.happykoo.kafkanoti.domain.notification.Notification;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

@SpringBootApplication
@SpringBootTest(classes = TestMongoConfig.class)
class NotificationRepositoryIntegrityTest {

  @Autowired
  private NotificationRepository sut;

  @BeforeEach
  void setup() {
    for (int i = 1; i <= 5; i++) {
      LocalDateTime occurredAt = LocalDateTime.now().minusDays(i);
      Notification notification = createCommentNotification("id-" + i, occurredAt);
      sut.save(notification);
    }
  }

  @AfterEach
  void tearDown() {
    sut.deleteAll();
  }

  @Test
  void test_save() {
    Optional<Notification> opt = sut.findById("id-1");
    assertAll(() -> assertTrue(opt.isPresent()),
        () -> assertThat(opt.get().getUserId()).isEqualTo(1L),
        () -> assertThat(opt.get().getType()).isEqualTo(COMMENT));
  }

  @Test
  void test_deleteById() {
    sut.deleteById("id-1");

    Optional<Notification> opt = sut.findById("id-1");
    assertAll(() -> assertFalse(opt.isPresent()));
  }

  @Test
  void test_findAllByUserIdOrderByOccurredAtDesc_firstQuery() {
    Pageable pageable = Pageable.ofSize(3);

    Slice<Notification> result = sut.findAllByUserIdOrderByOccurredAtDesc(1L, pageable);

    Notification first = result.getContent().get(0);
    Notification second = result.getContent().get(1);
    Notification third = result.getContent().get(2);

    assertAll(
        () -> assertThat(result.getContent().size()).isEqualTo(3),
        () -> assertTrue(result.hasNext()),
        () -> assertTrue(first.getOccurredAt().isAfter(second.getOccurredAt())),
        () -> assertTrue(second.getOccurredAt().isAfter(third.getOccurredAt()))
    );
  }

  @Test
  void test_findAllByUserIdAndOccurredAtLessThanOrderByOccurredAtDesc_secondQueryWithPivot() {
    Pageable pageable = PageRequest.ofSize(3);
    Slice<Notification> firstResult = sut.findAllByUserIdAndOccurredAtLessThanOrderByOccurredAtDesc(
        1L,
        LocalDateTime.now(),
        pageable);

    LocalDateTime pivot = firstResult.getContent().get(2).getOccurredAt();

    Slice<Notification> secondResult = sut.findAllByUserIdAndOccurredAtLessThanOrderByOccurredAtDesc(
        1L, pivot, pageable);

    Notification first = secondResult.getContent().get(0);
    Notification second = secondResult.getContent().get(1);

    assertAll(
        () -> assertThat(secondResult.getContent().size()).isEqualTo(2),
        () -> assertFalse(secondResult.hasNext()),
        () -> assertTrue(first.getOccurredAt().isAfter(second.getOccurredAt()))
    );
  }

  private Notification createCommentNotification(String id,
      LocalDateTime occurredAt) {
    return new CommentNotification(id,
        1L,
        3L,
        2L,
        2L,
        "test",
        occurredAt,
        LocalDateTime.now(),
        LocalDateTime.now(),
        LocalDateTime.now().plusDays(90));
  }
}