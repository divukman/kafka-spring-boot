package dev.lydtech.tracking.service;

import dev.lydtech.dispatch.message.DispatchPreparing;
import dev.lydtech.tracking.message.Status;
import dev.lydtech.tracking.message.TrackingStatusUpdated;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class StatusService {
    private final KafkaTemplate<String, Object> kafkaTemplate;

    private static final String TRACKING_STATUS_TOPIC = "tracking.status";

    public void process (final DispatchPreparing payload) throws Exception {
      // no op
      log.info("Status service service: payload: " + payload);

        final TrackingStatusUpdated trackingStatusUpdated = TrackingStatusUpdated.builder()
                .orderId(payload.getOrderId())
                .status(Status.PREPARING)
                .build();

        kafkaTemplate.send(StatusService.TRACKING_STATUS_TOPIC, trackingStatusUpdated).get();
    }
}
