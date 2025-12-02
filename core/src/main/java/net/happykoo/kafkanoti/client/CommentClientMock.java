package net.happykoo.kafkanoti.client;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import net.happykoo.kafkanoti.domain.notification.Comment;
import org.springframework.stereotype.Component;

@Component
public class CommentClientMock implements CommentClient {

  private final Map<Long, Comment> comments = new HashMap<>();

  public CommentClientMock() {
    comments.put(1L, new Comment(1L, 1L, "comment1", LocalDateTime.now()));
    comments.put(2L, new Comment(2L, 1L, "comment2", LocalDateTime.now()));
    comments.put(3L, new Comment(3L, 1L, "comment3", LocalDateTime.now()));
  }

  public Optional<Comment> getComment(Long id) {
    return Optional.ofNullable(comments.get(id));
  }
}
