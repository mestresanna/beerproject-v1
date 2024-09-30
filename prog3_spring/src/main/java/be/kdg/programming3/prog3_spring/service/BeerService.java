package be.kdg.programming3.prog3_spring.service;

import be.kdg.programming3.prog3_spring.Domain.Beer;
import be.kdg.programming3.prog3_spring.Domain.Containers;
import be.kdg.programming3.prog3_spring.Domain.Quantities;
import be.kdg.programming3.prog3_spring.repository.BeerRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BeerService {
    private BeerRepository beerRepository;

    public BeerService(BeerRepository beerRepository) {
        this.beerRepository = beerRepository;
    }

    public void addBeer(String name, double abv, int plato, String style, Quantities quantity, int stock, Containers containers, String brewery){
        Beer beer = new Beer(name, abv, plato, style, quantity, stock, containers, brewery);
        beerRepository.createBeer(beer);
    }

    public Beer getBeerById(int id) {
        return beerRepository.readBeer(id);
    }

    public List<Beer> getAllBeers() {
        return beerRepository.readAllBeers();
    }

}
