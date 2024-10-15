package be.kdg.programming3.prog3_spring.service;

import be.kdg.programming3.prog3_spring.Domain.Beer;
import be.kdg.programming3.prog3_spring.Domain.Containers;
import be.kdg.programming3.prog3_spring.Domain.Quantities;

import java.util.List;

public interface BeerService {
    void addBeer(String name, double abv, int plato, String style, Quantities quantity, int stock, Containers containers, String brewery, int beer);

    Beer getBeerById(int id);

    List<Beer> getAllBeers();
}
