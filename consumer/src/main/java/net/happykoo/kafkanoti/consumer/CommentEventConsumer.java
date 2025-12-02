package net.happykoo.kafkanoti.consumer;

import java.util.function.Consumer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.happykoo.kafkanoti.event.CommentEvent;
import net.happykoo.kafkanoti.event.CommentEventType;
import net.happykoo.kafkanoti.task.CommentAddTask;
import net.happykoo.kafkanoti.task.CommentRemoveTask;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class CommentEventConsumer {

  private final CommentAddTask commentAddTask;
  private final CommentRemoveTask commentRemoveTask;

  @Bean("comment")
  public Consumer<CommentEvent> comment() {
    return event -> {
      if (event.getType() == CommentEventType.ADD) {
        commentAddTask.processEvent(event);
      } else if (event.getType() == CommentEventType.REMOVE) {
        commentRemoveTask.processEvent(event);
      }
    };
  }
}
