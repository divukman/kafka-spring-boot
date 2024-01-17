package dev.lydtech.dispatch.handler;

import dev.lydtech.dispatch.message.OrderCreated;
import dev.lydtech.dispatch.service.DispatchService;
import dev.lydtech.dispatch.util.TestEventData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OrderCreatedHandlerTest {

    private OrderCreatedHandler handler;
    private DispatchService serviceMock;

    @BeforeEach
    void setUp() {
        serviceMock = mock(DispatchService.class);
        handler = new OrderCreatedHandler(serviceMock);
    }

    @Test
    void listen_Success() throws Exception {
        final OrderCreated payload = TestEventData.buildOrderCreatedEvent(UUID.randomUUID(), "testItem");
        handler.listen(payload);
        verify(serviceMock, times(1)).process(payload);
    }


    @Test
    public void listen_ServiceThrowsException() throws Exception {
        OrderCreated testEvent = TestEventData.buildOrderCreatedEvent(UUID.randomUUID(), UUID.randomUUID().toString());
        doThrow(new RuntimeException("Service Failure")).when(serviceMock).process(testEvent);

        handler.listen(testEvent);

        verify(serviceMock, times(1)).process(testEvent);
    }
}