package net.happykoo.kafkanoti.consumer;

import java.util.function.Consumer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.happykoo.kafkanoti.event.FollowEvent;
import net.happykoo.kafkanoti.event.FollowEventType;
import net.happykoo.kafkanoti.task.FollowTask;
import net.happykoo.kafkanoti.task.UnFollowTask;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class FollowEventConsumer {

  private final FollowTask followTask;
  private final UnFollowTask unFollowTask;

  @Bean("follow")
  public Consumer<FollowEvent> follow() {
    return event -> {
      if (event.getType() == FollowEventType.FOLLOW) {
        followTask.processEvent(event);
      } else if (event.getType() == FollowEventType.UNFOLLOW) {
        unFollowTask.processEvent(event);
      }

    };
  }
}
