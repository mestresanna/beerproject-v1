package be.kdg.programming3.prog3_spring.service;

import be.kdg.programming3.prog3_spring.Domain.Beer;
import be.kdg.programming3.prog3_spring.Domain.Customer;
import be.kdg.programming3.prog3_spring.Domain.Order;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public interface OrderService {
    void createOrder(Order order);
    void addOrder(String comments, Customer customer, HashMap<Beer, Integer> beers, String urlImg);

    Order getOrder(int idOrder);

    List<Order> getAllOrders();
}
