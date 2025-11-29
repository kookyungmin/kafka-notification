package net.happykoo.kafkanoti.event;

import java.time.Instant;
import lombok.Data;

@Data
public class CommentEvent {

  private CommentEventType type;
  private Long postId;
  private Long userId;
  private Long commentId;
  private Instant createdAt;
}
