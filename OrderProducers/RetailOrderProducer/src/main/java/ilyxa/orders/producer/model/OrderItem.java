package ilyxa.orders.producer.model;

public record OrderItem(String itemName, Long quantity, Double itemPrice) {
}
