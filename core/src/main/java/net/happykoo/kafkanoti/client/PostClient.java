package net.happykoo.kafkanoti.client;

import java.util.Optional;
import net.happykoo.kafkanoti.domain.notification.Post;

public interface PostClient {

  Optional<Post> getPost(Long id);

}
