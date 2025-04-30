package be.kdg.programming3.prog3_spring.repository.jpa;

import be.kdg.programming3.prog3_spring.Domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CustomerRepositoryJPA extends JpaRepository<Customer, Integer> {

}
