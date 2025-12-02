package net.happykoo.kafkanoti.client;

import java.util.Optional;
import net.happykoo.kafkanoti.domain.notification.Comment;

public interface CommentClient {

  Optional<Comment> getComment(Long id);

}
