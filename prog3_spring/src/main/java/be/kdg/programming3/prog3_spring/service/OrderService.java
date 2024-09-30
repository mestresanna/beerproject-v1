package be.kdg.programming3.prog3_spring.service;

import be.kdg.programming3.prog3_spring.Domain.Beer;
import be.kdg.programming3.prog3_spring.Domain.Costumer;
import be.kdg.programming3.prog3_spring.Domain.Order;
import be.kdg.programming3.prog3_spring.repository.OrderRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

@Component
public class OrderService {
    private OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void addOrder(String comments, Costumer costumer, HashMap<Beer, Integer> beers){
        LocalDate now = LocalDate.now();
        Order order = new Order(now, comments, costumer, beers);
        orderRepository.createOrder(order);
    }

    public Order getOrder(int idOrder){
        return orderRepository.readOrder(idOrder);
    }

    public List<Order> getAllOrders(){
        return orderRepository.readAllOrders();
    }
}
