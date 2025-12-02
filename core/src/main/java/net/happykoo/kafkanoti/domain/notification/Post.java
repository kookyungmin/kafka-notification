package net.happykoo.kafkanoti.domain.notification;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Post {

  private Long id;
  private Long writerId;
  private String imageUrl;
  private String content;
}
