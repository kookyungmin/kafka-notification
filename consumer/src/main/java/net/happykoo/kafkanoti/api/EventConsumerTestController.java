package net.happykoo.kafkanoti.api;

import java.util.function.Consumer;
import lombok.RequiredArgsConstructor;
import net.happykoo.kafkanoti.event.CommentEvent;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/test")
public class EventConsumerTestController implements EventConsumerTestControllerSpec {

  private final Consumer<CommentEvent> comment;

  @PostMapping("/comment")
  public void comment(@RequestBody CommentEvent event) {
    comment.accept(event);
  }
}
