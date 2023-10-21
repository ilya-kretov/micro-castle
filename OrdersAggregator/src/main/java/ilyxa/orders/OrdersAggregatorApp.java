package ilyxa.orders;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OrdersAggregatorApp {
    public static void main(String[] args) {
        System.out.println("Starting aggregator...");
        SpringApplication.run(OrdersAggregatorApp.class, args);
    }
}