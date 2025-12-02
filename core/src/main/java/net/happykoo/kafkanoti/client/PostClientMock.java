package net.happykoo.kafkanoti.client;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import net.happykoo.kafkanoti.domain.notification.Post;
import org.springframework.stereotype.Component;

@Component
public class PostClientMock implements PostClient {

  private final Map<Long, Post> posts = new HashMap<>();

  public PostClientMock() {
    posts.put(1L, new Post(1L, 111L, "imageUrl", "content1"));
    posts.put(2L, new Post(2L, 222L, "imageUrl", "content2"));
    posts.put(3L, new Post(3L, 222L, "imageUrl", "content3"));
  }

  public Optional<Post> getPost(Long id) {
    return Optional.ofNullable(posts.get(id));
  }
}
