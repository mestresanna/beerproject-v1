package be.kdg.programming3.prog3_spring.service;

import be.kdg.programming3.prog3_spring.Domain.Beer;
import be.kdg.programming3.prog3_spring.Domain.Customer;
import be.kdg.programming3.prog3_spring.Domain.Order;
import be.kdg.programming3.prog3_spring.repository.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class OrderServiceImp implements OrderService {
    private OrderRepository orderRepository;
    private Logger logger = LoggerFactory.getLogger(OrderServiceImp.class);

    public OrderServiceImp(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }


    @Override
    public void createOrder(Order order) {
        orderRepository.save(order);
    }

    @Override
    public void addOrder(String comments, Customer customer, HashMap<Beer, Integer> beers, String urlImg){
        Order order = new Order( comments, customer, beers,urlImg );
        logger.info("Order added: " + order);
        orderRepository.save(order);
    }



    @Override
    public Order getOrder(int idOrder){
        return orderRepository.findById(idOrder);
    }

    @Override
    public List<Order> getAllOrders(){
        return orderRepository.readAllOrders();
    }
}
