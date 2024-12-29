package be.kdg.programming3.prog3_spring.service;

import be.kdg.programming3.prog3_spring.Domain.Beer;
import be.kdg.programming3.prog3_spring.Domain.Containers;
import be.kdg.programming3.prog3_spring.Domain.Quantities;
import org.springframework.stereotype.Service;

import java.util.List;


public interface BeerService {
    void addBeer(Beer beer);
    void addBeer(String name, double abv, int plato, String style, Quantities quantity, int stock, Containers containers, String brewery, double price, String imageUrl);
    Beer getBeerById(int id);

    List<Beer> getAllBeers();
}
