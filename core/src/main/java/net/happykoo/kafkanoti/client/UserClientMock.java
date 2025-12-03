package net.happykoo.kafkanoti.client;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import net.happykoo.kafkanoti.domain.user.User;
import org.springframework.stereotype.Component;

@Component
public class UserClientMock implements UserClient {

  private final Map<Long, User> users = new HashMap<>();

  public UserClientMock() {
    users.put(1L, new User(1L, "user1", "imageUrl1"));
    users.put(2L, new User(2L, "user2", "imageUrl2"));
    users.put(3L, new User(3L, "user3", "imageUrl3"));
  }

  public Optional<User> getUser(Long id) {
    return Optional.ofNullable(users.get(id));
  }

  @Override
  public boolean isFollowing(Long userId, Long targetUserId) {
    //TODO: MSA User API 호출
    return false;
  }
}
