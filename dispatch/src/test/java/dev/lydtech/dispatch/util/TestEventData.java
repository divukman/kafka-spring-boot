package dev.lydtech.dispatch.util;

import dev.lydtech.dispatch.message.OrderCreated;

import java.util.UUID;

public class TestEventData {

    public static OrderCreated buildOrderCreatedEvent(final UUID orderId, final String item ) {
        return OrderCreated.builder()
                .orderId(orderId)
                .item(item)
                .build();
    }
}
