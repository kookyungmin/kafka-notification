package net.happykoo.kafkanoti.domain.notification;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Comment {

  private Long id;
  private Long writerId;
  private String content;
  private LocalDateTime createdAt;
}
