package be.kdg.programming3.prog3_spring.repository;

import be.kdg.programming3.prog3_spring.Domain.Beer;
import be.kdg.programming3.prog3_spring.Domain.Order;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class OrderRepositoryImp implements OrderRepository {
    private static List<Order> orders = new ArrayList<>();

    @Override
    public Order createOrder(Order order) {
        orders.add(order);
        order.setIdOrder(orders.size() + 1);
        setOrderToBeer(order);
        return order;
    }

    @Override
    public void setOrderToBeer(Order order){
        HashMap<Beer, Integer> beers = order.getBeers();
        if (beers!=null && beers.size()>0) {
            for (Map.Entry<Beer, Integer> entry : beers.entrySet()) {
                Beer key = entry.getKey();
                key.setOrders(order);
            }
        }
    }

    @Override
    public Order readOrder(int orderId) {
        return orders.get(orderId - 1);
    }

    @Override
    public List<Order> readAllOrders() {
        return orders;
    }

}
