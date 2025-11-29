package net.happykoo.kafkanoti.repository;

import net.happykoo.kafkanoti.domain.notification.Notification;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends MongoRepository<Notification, String> {

}
