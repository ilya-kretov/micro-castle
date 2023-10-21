package ilyxa.orders.producer.model;

import java.util.List;

public record Order(String type, String title, Double price, List<OrderItem> items) {
}
