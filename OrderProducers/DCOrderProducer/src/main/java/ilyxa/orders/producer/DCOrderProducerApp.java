package ilyxa.orders.producer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
@EnableScheduling
public class DCOrderProducerApp {
    public static void main(String[] args) {
        System.out.println("Starting dc order producer...");
        SpringApplication.run(DCOrderProducerApp.class);
    }
}