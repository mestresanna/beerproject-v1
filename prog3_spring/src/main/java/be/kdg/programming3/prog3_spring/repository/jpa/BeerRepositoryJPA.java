package be.kdg.programming3.prog3_spring.repository.jpa;

import be.kdg.programming3.prog3_spring.Domain.Beer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BeerRepositoryJPA extends JpaRepository<Beer, Integer> {

}
