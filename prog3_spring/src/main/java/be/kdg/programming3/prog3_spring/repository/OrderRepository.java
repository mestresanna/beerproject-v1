package be.kdg.programming3.prog3_spring.repository;

import be.kdg.programming3.prog3_spring.Domain.Order;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderRepository {
    private static List<Order> orders = new ArrayList<>();

    public Order createOrder(Order order) {
        orders.add(order);
        order.setIdOrder(orders.size() + 1);
        return order;
    }

    public Order readOrder(int orderId) {
        return orders.get(orderId - 1);
    }

    public List<Order> readAllOrders() {
        return orders;
    }

}
