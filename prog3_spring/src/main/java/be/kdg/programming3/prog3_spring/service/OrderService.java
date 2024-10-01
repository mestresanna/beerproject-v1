package be.kdg.programming3.prog3_spring.service;

import be.kdg.programming3.prog3_spring.Domain.Beer;
import be.kdg.programming3.prog3_spring.Domain.Costumer;
import be.kdg.programming3.prog3_spring.Domain.Order;

import java.util.HashMap;
import java.util.List;

public interface OrderService {
    void addOrder(String comments, Costumer costumer, HashMap<Beer, Integer> beers);

    Order getOrder(int idOrder);

    List<Order> getAllOrders();
}
