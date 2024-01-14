package dev.lydtech.dispatch.service;

import dev.lydtech.dispatch.message.OrderCreated;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DispatchService {

    public void process (final OrderCreated payload) {
      // no op
      log.info("Dispatch service: payload: " + payload);
    }
}
