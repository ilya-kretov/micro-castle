package ilyxa.orders.producer;

import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import ilyxa.orders.producer.model.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.ActiveProfiles;

import java.util.concurrent.CompletableFuture;

import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ActiveProfiles("test")
@SpringBootTest
class OrderPublisherServiceTest {
    @MockBean
    private KafkaTemplate<String, Object> template;

    @BeforeEach
    void setUp() {
    }

    @Captor
    private ArgumentCaptor<Object> sendCaptor;

    @Test
    void generateOrder() {
        OrderPublisherService publisherService = new OrderPublisherService(template);

        publisherService.setKafkaTemplate(template);
        Mockito.when(template.send(any(String.class), any(Object.class))).thenReturn(new CompletableFuture<>());
        publisherService.generateOrder();

        verify(template, times(1)).send(any(String.class), sendCaptor.capture());
        Order order = (Order) sendCaptor.getValue();
        assertEquals("ReplenishmentOrder", order.type());
    }
}