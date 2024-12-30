package be.kdg.programming3.prog3_spring.repository;

import be.kdg.programming3.prog3_spring.Domain.Beer;
import be.kdg.programming3.prog3_spring.Domain.Customer;
import be.kdg.programming3.prog3_spring.Domain.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@Repository
public class OrderRepositoryImp implements OrderRepository {
    private static List<Order> orders = new ArrayList<>();
    private Logger logger = LoggerFactory.getLogger(OrderRepositoryImp.class);

    @Override
    public Order save(Order order) {
        orders.add(order);
        order.setDate(LocalDate.now());
        order.setIdOrder(orders.size()-1);
        setOrderToBeer(order);
        setOrderToCustomer(order);
        logger.info("Creating new beer: {}, with id: {}", order, order.getIdOrder());
        return order;
    }

    @Override
    public void setOrderToCustomer(Order order){
        Customer customer = order.getCustomer();
        customer.setOrders(order.getIdOrder());
    }

    @Override
    public void setOrderToBeer(Order order){
        HashMap<Beer, Integer> beers = order.getBeers();
        if (beers!=null && !beers.isEmpty()) {
            for (Map.Entry<Beer, Integer> entry : beers.entrySet()) {
                Beer key = entry.getKey();
                key.setOrders(order.getIdOrder());
                logger.debug(key.toString());
            }
        }
    }

    @Override
    public Order findById(int orderId) {
        return orders.get(orderId);
    }

    @Override
    public List<Order> readAllOrders() {
        return orders;
    }



}
