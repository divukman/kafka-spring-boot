package dev.lydtech.tracking.handler;


import dev.lydtech.dispatch.message.DispatchPreparing;
import dev.lydtech.tracking.service.StatusService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class TrackingHandler {

    private static final String DISPATCH_TRACKING_TOPIC = "dispatch.tracking";

    private final StatusService dispatchService;

    @KafkaListener(
            id="trackingConsumerClient",
            topics = DISPATCH_TRACKING_TOPIC,
            groupId = "dispatch.order.created.consumer",
            containerFactory = "kafkaListenerContainerFactory"
    )
    public void listen(final DispatchPreparing payload) {
        log.info("Message received: " + payload);
        try {
            dispatchService.process(payload);
        } catch (Exception e) {
            log.error("Processing failure: ", e);
        }
    }
}
