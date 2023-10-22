package ilyxa.orders.producer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.TestConfiguration;

@TestConfiguration
public class TestConfig {
    @Value("${order-producer.new-order-topic-name}")
    private String topicName;
}
