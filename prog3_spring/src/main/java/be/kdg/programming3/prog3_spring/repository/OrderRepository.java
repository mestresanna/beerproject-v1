package be.kdg.programming3.prog3_spring.repository;

import be.kdg.programming3.prog3_spring.Domain.Beer;
import be.kdg.programming3.prog3_spring.Domain.Customer;
import be.kdg.programming3.prog3_spring.Domain.Order;

import java.util.List;


public interface OrderRepository {
    Order save(Order order);

    void loadBeerOrder(Order order);

    void loadCustomer(Order order);

    Order findById(int orderId);

    List<Order> readAllOrders();

    void loadBeer(Beer beer, Order order);

    List<Order> findByBeer(Beer beer);

    List<Order> findByCustomer(Customer customer);
    void delete(int id);
    void deleteBeer(int id, int beerId);
}
