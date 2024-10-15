package be.kdg.programming3.prog3_spring.repository;

import be.kdg.programming3.prog3_spring.Domain.Beer;

import java.util.List;

public interface BeerRepository {
    Beer createBeer(Beer beer);

    Beer readBeer(int idBeer);

    List<Beer> readAllBeers();

    int getSize();
    int getStock(int idBeer);
}
