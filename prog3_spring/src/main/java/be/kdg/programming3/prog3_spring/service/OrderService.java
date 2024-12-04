package be.kdg.programming3.prog3_spring.service;

import be.kdg.programming3.prog3_spring.Domain.Beer;
import be.kdg.programming3.prog3_spring.Domain.Customer;
import be.kdg.programming3.prog3_spring.Domain.Order;

import java.util.HashMap;
import java.util.List;

public interface OrderService {
    void addOrder(String comments, Customer customer, HashMap<Beer, Integer> beers, String urlImg);

    Order getOrder(int idOrder);

    List<Order> getAllOrders();
}
