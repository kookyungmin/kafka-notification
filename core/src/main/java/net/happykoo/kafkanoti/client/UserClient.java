package net.happykoo.kafkanoti.client;

import java.util.Optional;
import net.happykoo.kafkanoti.domain.user.User;

public interface UserClient {

  Optional<User> getUser(Long id);

  boolean isFollowing(Long userId, Long targetUserId);
}
