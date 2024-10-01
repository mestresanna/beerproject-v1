package be.kdg.programming3.prog3_spring;

import be.kdg.programming3.prog3_spring.presentation.Menu;
import be.kdg.programming3.prog3_spring.repository.*;
import be.kdg.programming3.prog3_spring.service.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
    @Bean
    public Menu menu(CostumerService costumerService, BeerService beerService, OrderService orderService) {
        return new Menu(orderService, beerService, costumerService);
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
    public CostumerService costumerService(CostumerRepository costumerRepository) {
        return new CostumerServiceImp(costumerRepository);
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
    public CostumerRepository costumerRepository() {
        return new CostumerRepositoryImp();
    }

}
