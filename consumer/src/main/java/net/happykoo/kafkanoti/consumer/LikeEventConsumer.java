package net.happykoo.kafkanoti.consumer;

import java.util.function.Consumer;
import lombok.extern.slf4j.Slf4j;
import net.happykoo.kafkanoti.event.LikeEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class LikeEventConsumer {

  @Bean("like")
  public Consumer<LikeEvent> like() {
    return event -> log.info(event.toString());
  }
}
