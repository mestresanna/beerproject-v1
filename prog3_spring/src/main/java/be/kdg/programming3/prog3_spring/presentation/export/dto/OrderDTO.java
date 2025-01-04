package be.kdg.programming3.prog3_spring.presentation.export.dto;

import be.kdg.programming3.prog3_spring.Domain.Order;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.HashMap;


public class OrderDTO {
    private Logger logger = LoggerFactory.getLogger(OrderDTO.class);

    private int idOrder;
    private LocalDate date;
    private String comments;
    private CustomerDTO customer;
    private HashMap<BeerDTO, Integer> beers;
    private double total;


    public OrderDTO(Order order) {
        this.idOrder = order.getIdOrder();
        this.date = order.getDate();
        this.comments = order.getComments();
        this.customer = new CustomerDTO(order.getCustomer());
        HashMap<BeerDTO, Integer> beersDTO = new HashMap<>();
        if (order.getBeers() != null) {

            logger.debug("getBeers is not null");

            order.getBeers().forEach((beer, quantity) -> {
                BeerDTO beerDTO = new BeerDTO(beer);
                beersDTO.put(beerDTO, quantity);
            });
        }
        this.beers = beersDTO;
        this.total = order.getTotal();
    }

}
