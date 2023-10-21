package ilyxa.orders.producer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class RetailOrderProducerApp {
    public static void main(String[] args) {
        SpringApplication.run(RetailOrderProducerApp.class);
    }
}