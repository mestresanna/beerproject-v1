package be.kdg.programming3.prog3_spring.repository;

import be.kdg.programming3.prog3_spring.Domain.Order;

import java.util.List;

public interface OrderRepository {
    Order createOrder(Order order);

    void setOrderToBeer(Order order);

    Order readOrder(int orderId);

    List<Order> readAllOrders();
}
