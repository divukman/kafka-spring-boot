package dev.lydtech.dispatch.service;

import dev.lydtech.dispatch.message.DispatchPreparing;
import dev.lydtech.dispatch.message.OrderCreated;
import dev.lydtech.dispatch.message.OrderDispatched;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class DispatchService {
    private final KafkaTemplate<String, Object> kafkaTemplate;

    private static final String ORDER_DISPATCHED_TOPIC = "order.dispatched";
    private static final String DISPATCH_TRACKING_TOPIC = "dispatch.tracking";

    public void process (final OrderCreated payload) throws Exception {
      // no op
      log.info("Dispatch service: payload: " + payload);

        OrderDispatched orderDispatched = OrderDispatched.builder()
                .orderId(payload.getOrderId())
                .build();

        final DispatchPreparing dispatchPreparing = DispatchPreparing.builder()
                .orderId(payload.getOrderId())
                .build();

        kafkaTemplate.send(DispatchService.ORDER_DISPATCHED_TOPIC, orderDispatched).get();
        kafkaTemplate.send(DispatchService.DISPATCH_TRACKING_TOPIC, dispatchPreparing).get();
    }
}
