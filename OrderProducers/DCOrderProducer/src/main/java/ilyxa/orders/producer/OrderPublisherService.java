package ilyxa.orders.producer;

import com.github.javafaker.Faker;
import ilyxa.orders.model.Order;
import ilyxa.orders.model.OrderItem;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Component
@RequiredArgsConstructor
@Getter
@Slf4j
public class OrderPublisherService {

    @NonNull
    private KafkaTemplate<String, Object> kafkaTemplate;

    private String topicName;
    private final Faker faker = new Faker();

    @Autowired
    public void setKafkaTemplate(@NonNull KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Value("${order-producer.new-order-topic-name}")
    public void setTopicName(String value) {
        this.topicName = value;
    }

    private long id = 1;

    @Scheduled(fixedRate = 2000L)
    public void generateOrder() {
        String productName1 = this.faker.commerce().productName();
        String productName2 = this.faker.commerce().productName();
        double price1 = Double.parseDouble(this.faker.commerce().price());
        long quantity1 = faker.random().nextInt(1, 10);
        double price2 = Double.parseDouble(this.faker.commerce().price());
        long quantity2 = faker.random().nextInt(1, 10);
        String key = "Replenish" + id;
        Order order = new Order(
                key,
                "ReplenishmentOrder",
                "Replenish " + productName1 + " and " + productName2,
                price1 * quantity1 + price2 * quantity2,
                Arrays.asList(
                        new OrderItem(productName1, quantity1, price1),
                        new OrderItem(productName2, quantity2, price2))
        );

        log.info("sending order: " + order.title());
        this.kafkaTemplate.send(this.topicName, key, order);
        id++;
    }
}
