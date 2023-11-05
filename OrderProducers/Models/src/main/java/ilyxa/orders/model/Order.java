package ilyxa.orders.model;

import java.util.List;

public record Order(String orderId, String type, String title, Double price, List<ilyxa.orders.model.OrderItem> items) {
}
