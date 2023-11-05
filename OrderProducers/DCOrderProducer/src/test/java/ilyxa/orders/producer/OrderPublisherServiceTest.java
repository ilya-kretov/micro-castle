package ilyxa.orders.producer;

import ilyxa.orders.model.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.ActiveProfiles;

import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ActiveProfiles("test")
class OrderPublisherServiceTest {
    @BeforeEach
    void setUp() {
    }

    @SuppressWarnings("unchecked")
    @Test
    void generateOrder() {
        ArgumentCaptor<Object> sendCaptor = ArgumentCaptor.forClass(Object.class);
        KafkaTemplate<String, Object> template = (KafkaTemplate<String, Object>)Mockito.mock(KafkaTemplate.class);

        OrderPublisherService publisherService = new OrderPublisherService(template);

        publisherService.setKafkaTemplate(template);
        publisherService.setTopicName("test-topic");
        when(template.send(any(String.class), any(String.class), any(Object.class))).thenReturn(new CompletableFuture<>());
        publisherService.generateOrder();

        verify(template, times(1)).send(
                any(String.class),
                any(String.class),
                sendCaptor.capture());
        Order order = (Order) sendCaptor.getValue();
        assertEquals("ReplenishmentOrder", order.type());
    }
}