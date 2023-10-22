package ilyxa.orders.producer;

import ilyxa.orders.model.Order;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ActiveProfiles("test")
//@SpringBootTest("spring.autoconfigure.exclude=org.springframework.boot.autoconfigure.kafka.KafkaAutoConfiguration")
class OrderPublisherServiceTest {
//    @MockBean
//    private KafkaTemplate<String, Object> template;

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
        publisherService.setTopicName("asdf");
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