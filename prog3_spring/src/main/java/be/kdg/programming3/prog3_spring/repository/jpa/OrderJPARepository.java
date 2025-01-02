package be.kdg.programming3.prog3_spring.repository.jpa;

import be.kdg.programming3.prog3_spring.Domain.Beer;
import be.kdg.programming3.prog3_spring.Domain.Customer;
import be.kdg.programming3.prog3_spring.Domain.Order;
import be.kdg.programming3.prog3_spring.Domain.OrderBeer;
import be.kdg.programming3.prog3_spring.repository.OrderRepository;
import be.kdg.programming3.prog3_spring.service.BeerService;
import be.kdg.programming3.prog3_spring.service.CustomerService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@Profile("jpa")
public class OrderJPARepository implements OrderRepository {
    private final CustomerService customerService;
    private final BeerService beerService;
    private Logger logger = LoggerFactory.getLogger(OrderJPARepository.class);

   @PersistenceContext
   private EntityManager em;

    public OrderJPARepository(CustomerService customerService, BeerService beerService) {
      this.customerService = customerService;
        this.beerService = beerService;
    }

    @Transactional
    @Override
    public Order save(Order order) {
        logger.debug("Creat order: {}", order);
        order.setDate(LocalDate.now());
        order.setImageUrl("/images/shopping-cart.png");
        em.persist(order);
        for (OrderBeer orderBeer : order.getOrderBeers()) {
            em.persist(orderBeer);
        }
        em.flush();
        loadBeerOrder(order);
        return order;
    }

    @Override
    public void loadCustomer(Order order){
        logger.debug("Setting order to customer: {}", order);

    }

    @Transactional
    @Override
    public void loadBeerOrder(Order order){
        List<OrderBeer> orderBeers = new ArrayList<>(order.getOrderBeers());
        orderBeers.forEach(orderBeer -> {
            Beer beer = orderBeer.getBeer();
            int updatedStock = beer.getStock() - orderBeer.getQuantity();
            beer.setStock(updatedStock);
            beerService.updateBeer(beer);
            logger.debug("Loaded beer {} for order {}", beer.getName(), order.getIdOrder());
        });
    }

    @Transactional
    @Override
    public void loadBeer(Beer beer, Order order){
        em.createQuery("SELECT b FROM OrderBeer b WHERE order = :order AND beer = :beer", OrderBeer.class)
                .getResultStream().forEach(b -> {
                    int quantity = beer.getStock() - b.getQuantity();
                    beer.setStock(quantity);
                    findByBeer(beer);
                    beerService.updateBeer(beer);
                });

    }

    @Override
    public List<Order> findByBeer(Beer beer){
        return em.createQuery("SELECT o FROM Order o JOIN o.orderBeers ob WHERE ob.beer = :beer", Order.class)
                .setParameter("beer", beer)
                .getResultList();
    }

    @Override
    public List<Order> findByCustomer(Customer customer){
        return em.createQuery("SELECT o FROM Order o WHERE o.customer = :customer", Order.class)
                .setParameter("customer", customer)
                .getResultList();
    }

    @Override
    public Order findById(int orderId) {
        logger.debug("Finding order with id: {}", orderId);
        return em.find(Order.class, orderId);
    }

    @Override
    public List<Order> readAllOrders() {
        logger.debug("Reading all orders");
        List<Order> orders = em.createQuery("SELECT o FROM Order o", Order.class).getResultList();
        return orders;
    }

    @Transactional
    @Override
    public void delete(int id) {
        Order order = em.find(Order.class, id);
        if (order != null) {
            em.remove(order);
            logger.debug("Deleted order with id: {}", id);
        } else {
            logger.warn("Order with id {} not found.", id);
        }
    }

    @Transactional
    @Override
    public void updateOrder(Order order) {
        em.merge(order);
    }

    @Transactional
    @Override
    public void deleteBeer(int orderId, int beerId) {
        Beer beer = em.find(Beer.class, beerId);
        Order order = em.find(Order.class, orderId);
        if (order != null) {
            OrderBeer orderBeerToRemove = order.getOrderBeers().stream()
                    .filter(ob -> ob.getBeer().getIdBeer() == beerId)
                    .findFirst()
                    .orElse(null);

            if (orderBeerToRemove != null) {
                // Update stock beer
                int quantity = orderBeerToRemove.getQuantity();
                beer.setStock(beer.getStock() + quantity);
                em.merge(beer);

                // Update total price order
                double price = beer.getPrice();
                order.setTotal(order.getTotal() - (quantity * price));

                // Delete OrderBeer from the collection
                order.getOrderBeers().remove(orderBeerToRemove);
                em.merge(order);

                // Delete orderBeer from database
                em.remove(orderBeerToRemove);
            }
        }
    }

}
