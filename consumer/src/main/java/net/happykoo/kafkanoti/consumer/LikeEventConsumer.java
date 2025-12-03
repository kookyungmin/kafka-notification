package net.happykoo.kafkanoti.consumer;

import java.util.function.Consumer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.happykoo.kafkanoti.event.LikeEvent;
import net.happykoo.kafkanoti.event.LikeEventType;
import net.happykoo.kafkanoti.task.LikeAddTask;
import net.happykoo.kafkanoti.task.LikeRemoveTask;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class LikeEventConsumer {

  private final LikeAddTask likeAddTask;
  private final LikeRemoveTask likeRemoveTask;

  @Bean("like")
  public Consumer<LikeEvent> like() {
    return event -> {
      if (event.getType() == LikeEventType.ADD) {
        likeAddTask.processEvent(event);
      } else if (event.getType() == LikeEventType.REMOVE) {
        likeRemoveTask.processEvent(event);
      }
    };
  }
}
