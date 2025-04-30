package be.kdg.programming3.prog3_spring.repository.jpa;

import be.kdg.programming3.prog3_spring.Domain.Beer;
import be.kdg.programming3.prog3_spring.Domain.Customer;
import be.kdg.programming3.prog3_spring.Domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepositoryJPA extends JpaRepository<Order, Integer> {

    List<Order> findByCustomer(Customer customer);

    @Query("SELECT o FROM Order o JOIN o.orderBeers ob WHERE ob.beer = :beer")
    List<Order> findByBeer(@Param("beer") Beer beer);


}
