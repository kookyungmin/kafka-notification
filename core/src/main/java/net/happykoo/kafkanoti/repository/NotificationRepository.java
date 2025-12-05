package net.happykoo.kafkanoti.repository;

import java.time.LocalDateTime;
import java.util.Optional;
import net.happykoo.kafkanoti.domain.notification.Notification;
import net.happykoo.kafkanoti.domain.notification.NotificationType;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends MongoRepository<Notification, String> {

  @Query("{ 'type': ?0, 'commentId': ?1 }")
  Optional<Notification> findByTypeAndCommentId(NotificationType type, Long commentId);

  @Query("{ 'type':  ?0, 'postId':  ?1 }")
  Optional<Notification> findByTypeAndPostId(NotificationType type, Long postId);

  @Query("{ 'type':  ?0, 'userId':  ?1, 'targetUserId': ?2 }")
  Optional<Notification> findByTypeAndUserIdAndTargetUserId(NotificationType type, Long userId,
      Long targetUserId);

  Slice<Notification> findAllByUserIdOrderByOccurredAtDesc(Long UserId, Pageable pageable);

  Slice<Notification> findAllByUserIdAndOccurredAtLessThanOrderByOccurredAtDesc(Long UserId,
      LocalDateTime occurredAt,
      Pageable pageable);

  Optional<Notification> findFirstByUserIdOrderByLastUpdatedAtDesc(Long UserId);
}
