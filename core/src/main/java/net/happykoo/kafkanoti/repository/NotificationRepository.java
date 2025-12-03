package net.happykoo.kafkanoti.repository;

import java.util.Optional;
import net.happykoo.kafkanoti.domain.notification.Notification;
import net.happykoo.kafkanoti.domain.notification.NotificationType;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends MongoRepository<Notification, String> {

  @Query("{ 'type': ?0, 'commentId': ?1 }")
  Optional<Notification> findByTypeAndCommentId(NotificationType type, Long commentId);

  @Query("{ 'type':  ?0, 'postId':  ?1 }")
  Optional<Notification> findByTypeAndPostId(NotificationType type, Long postId);
}
