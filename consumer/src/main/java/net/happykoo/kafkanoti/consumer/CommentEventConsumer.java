package net.happykoo.kafkanoti.consumer;

import java.util.function.Consumer;
import lombok.extern.slf4j.Slf4j;
import net.happykoo.kafkanoti.event.CommentEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CommentEventConsumer {

  @Bean("comment")
  public Consumer<CommentEvent> comment() {
    return event -> log.info(event.toString());
  }
}
