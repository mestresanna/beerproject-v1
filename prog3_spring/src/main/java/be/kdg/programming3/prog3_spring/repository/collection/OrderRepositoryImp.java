package be.kdg.programming3.prog3_spring.repository.collection;

import be.kdg.programming3.prog3_spring.Domain.Beer;
import be.kdg.programming3.prog3_spring.Domain.Customer;
import be.kdg.programming3.prog3_spring.Domain.Order;
import be.kdg.programming3.prog3_spring.repository.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@Profile("collections")
public class OrderRepositoryImp implements OrderRepository {
    private static List<Order> orders = new ArrayList<>();
    private Logger logger = LoggerFactory.getLogger(OrderRepositoryImp.class);

    @Override
    public Order save(Order order) {
        orders.add(order);
        order.setDate(LocalDate.now());
        order.setIdOrder(orders.size()-1);
        loadBeerOrder(order);
        loadCustomer(order);
        logger.info("Creating new beer: {}, with id: {}", order, order.getIdOrder());
        return order;
    }

    @Override
    public void loadCustomer(Order order){
        Customer customer = order.getCustomer();
        customer.setOrders(order);
    }

    @Override
    public void loadBeerOrder(Order order){
        HashMap<Beer, Integer> beers = order.getBeers();
        if (beers!=null && !beers.isEmpty()) {
            for (Map.Entry<Beer, Integer> entry : beers.entrySet()) {
                Beer key = entry.getKey();
                key.setOrder(order);
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

    @Override
    public void loadBeer(Beer beer, Order order) {
    }

    @Override
    public List<Order> findByBeer(Beer beer) {
        return beer.getOrders();
    }

    @Override
    public List<Order> findByCustomer(Customer customer) {
        return customer.getOrders();
    }


}
