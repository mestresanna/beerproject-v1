package be.kdg.programming3.prog3_spring.config;

import be.kdg.programming3.prog3_spring.presentation.console.Menu;
import be.kdg.programming3.prog3_spring.repository.*;
import be.kdg.programming3.prog3_spring.service.*;
import org.springframework.context.annotation.Bean;

//@Configuration
public class Config {
    @Bean
    public Menu menu(CustomerService customerService, BeerService beerService, OrderService orderService) {
        return new Menu(orderService, beerService, customerService);
    }

    @Bean
    public BeerService beerService(BeerRepository beerRepository) {
        return new BeerServiceImp(beerRepository);
    }

    @Bean
    public OrderService orderService(OrderRepository orderRepository) {
        return new OrderServiceImp(orderRepository);
    }

    @Bean
    public CustomerService costumerService(CustomerRepository customerRepository) {
        return new CustomerServiceImp(customerRepository);
    }
    @Bean
    public BeerRepository beerRepository() {
        return new BeerRepositoryImp();
    }

    @Bean
    public OrderRepository orderRepository() {
        return new OrderRepositoryImp();
    }

    @Bean
    public CustomerRepository costumerRepository() {
        return new CustomerRepositoryImp();
    }

}
