package be.kdg.programming3.prog3_spring.repository;

import be.kdg.programming3.prog3_spring.Domain.Order;

import java.util.List;


public interface OrderRepository {
    Order save(Order order);

    void setOrderToBeer(Order order);

    void setOrderToCustomer(Order order);

    Order findById(int orderId);

    List<Order> readAllOrders();

}
