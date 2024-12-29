package be.kdg.programming3.prog3_spring.repository;

import be.kdg.programming3.prog3_spring.Domain.Beer;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface BeerRepository {
    Beer save(Beer beer);

    Beer findById(int idBeer);

    List<Beer> readAllBeers();

    int getSize();
    int getStock(int idBeer);
}
