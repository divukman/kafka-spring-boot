package dev.lydtech.dispatch.handler;

import dev.lydtech.dispatch.service.DispatchService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
    void listen() {
        final String payload = "payload";
        handler.listen(payload);
        verify(serviceMock, times(1)).process(payload);
    }
}