package be.kdg.programming3.prog3_spring.service.jpa;

import be.kdg.programming3.prog3_spring.Domain.Beer;
import be.kdg.programming3.prog3_spring.Domain.Customer;
import be.kdg.programming3.prog3_spring.Domain.Order;
import be.kdg.programming3.prog3_spring.Domain.OrderBeer;
import be.kdg.programming3.prog3_spring.repository.jpa.BeerRepositoryJPA;
import be.kdg.programming3.prog3_spring.repository.jpa.OrderRepositoryJPA;
import be.kdg.programming3.prog3_spring.service.OrderService;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@Profile("jpa_rep")
public class OrderServiceJPA implements OrderService {
    private final OrderRepositoryJPA orderRepository;
    private Logger logger = LoggerFactory.getLogger(OrderServiceJPA.class);
    private BeerRepositoryJPA beerRepository;

    public OrderServiceJPA(OrderRepositoryJPA orderRepository, BeerRepositoryJPA beerRepository) {
        this.orderRepository = orderRepository;
        this.beerRepository = beerRepository;
    }


    @Transactional
    @Override
    public void createOrder(Order order) {
        orderRepository.save(order);
        List<OrderBeer> orderBeers = new ArrayList<>(order.getOrderBeers());
        orderBeers.forEach(
                orderBeer -> {
                    Beer beer = orderBeer.getBeer();
                    if (beer != null) {
                        setBeerStock(beer, orderBeer.getQuantity());
                    }
                });
        logger.info("Saving order in Service: {}", order);
    }

    @Override
    public void addOrder(String comments, Customer customer, HashMap<Beer, Integer> beers, String urlImg){
        Order order = new Order( comments, customer, beers,urlImg );
        logger.info("Order added: " + order);
        //orderRepository.save(order);
    }

    @Override
    public Order getOrder(int idOrder){
        return orderRepository.findById(idOrder).orElse(null);
    }

    @Override
    public List<Order> getAllOrders(){
        return orderRepository.findAll();
    }

    @Override
    public List<Order> findOrdersByCustomer(Customer customer) {
        return orderRepository.findByCustomer(customer);
    }

    @Override
    public List<Order> findOrdersByBeer(Beer beer) {
        return orderRepository.findByBeer(beer);
    }

    @Transactional
    @Override
    public void delete(int id){
        orderRepository.deleteById(id);
    }

    @Transactional
    @Override
    public void updateOrder(Order order){
        orderRepository.save(order);
    }

    @Transactional
    @Override
    public void deleteBeer(int order, int beer) {
        deleteBeerFromOrder(order, beer);
    }

    @Transactional
    public void deleteBeerFromOrder(int orderId, int beerId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order not found: " + orderId));

        OrderBeer orderBeerToRemove = order.getOrderBeers().stream()
                .filter(ob -> ob.getBeer().getIdBeer() == beerId)
                .findFirst()
                .orElse(null);

        if (orderBeerToRemove != null) {
            order.removeOrderBeer(orderBeerToRemove);
            resetBeerStock(beerId, orderBeerToRemove.getQuantity());
            resetTotalPrice(beerId, orderBeerToRemove.getQuantity(), order);
            orderRepository.save(order);
        }
    }

    public void setBeerStock(Beer beer, int quantity) {
        Beer managedBeer = beerRepository.findById(beer.getIdBeer())
                .orElseThrow(() -> new IllegalArgumentException("Beer not found: " + beer.getIdBeer()));

        int newStock = managedBeer.getStock() - quantity;
        if (newStock < 0) {
            throw new IllegalStateException("Not enough stock for beer: " + managedBeer.getName());
        }
        managedBeer.setStock(newStock);
        logger.info("Beer stock updated: " + managedBeer.getStock());
        beerRepository.save(managedBeer);
    }

    public void resetBeerStock(int beerId, int quantity) {
        Beer beer = beerRepository.findById(beerId)
                .orElseThrow(() -> new IllegalArgumentException("Beer not found: " + beerId));

        beer.setStock(beer.getStock() + quantity);
        beerRepository.save(beer);
    }

    public void resetTotalPrice(int beerId, int quantity, Order order) {
        Beer beer = beerRepository.findById(beerId)
                .orElseThrow(() -> new IllegalArgumentException("Beer not found: " + beerId));

        double price = beer.getPrice() * quantity;

        order.setTotal(order.getTotal() - price);
    }

}
