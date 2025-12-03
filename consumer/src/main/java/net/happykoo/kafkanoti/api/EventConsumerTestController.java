package net.happykoo.kafkanoti.api;

import java.util.function.Consumer;
import lombok.RequiredArgsConstructor;
import net.happykoo.kafkanoti.event.CommentEvent;
import net.happykoo.kafkanoti.event.FollowEvent;
import net.happykoo.kafkanoti.event.LikeEvent;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/test")
public class EventConsumerTestController implements EventConsumerTestControllerSpec {

  private final Consumer<CommentEvent> comment;
  private final Consumer<LikeEvent> like;
  private final Consumer<FollowEvent> follow;

  @PostMapping("/comment")
  @Override
  public void comment(@RequestBody CommentEvent event) {
    comment.accept(event);
  }

  @PostMapping("/like")
  @Override
  public void like(@RequestBody LikeEvent event) {
    like.accept(event);
  }

  @PostMapping("/follow")
  @Override
  public void follow(@RequestBody FollowEvent event) {
    follow.accept(event);
  }
}
