package be.kdg.programming3.prog3_spring.repository;

import be.kdg.programming3.prog3_spring.Domain.Order;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface OrderRepository {
    Order createOrder(Order order);

    void setOrderToBeer(Order order);

    void setOrderToCustomer(Order order);

    Order readOrder(int orderId);

    List<Order> readAllOrders();
}
