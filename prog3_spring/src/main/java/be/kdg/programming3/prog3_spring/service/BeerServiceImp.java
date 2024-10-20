package be.kdg.programming3.prog3_spring.service;

import be.kdg.programming3.prog3_spring.Domain.Beer;
import be.kdg.programming3.prog3_spring.Domain.Containers;
import be.kdg.programming3.prog3_spring.Domain.Quantities;
import be.kdg.programming3.prog3_spring.repository.BeerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;


public class BeerServiceImp implements BeerService {
    private Logger logger = LoggerFactory.getLogger(BeerServiceImp.class);
    private BeerRepository beerRepository;

    public BeerServiceImp(BeerRepository beerRepository) {
        logger.info("Creating Beer Service");
        this.beerRepository = beerRepository;
    }

    @Override
    public Beer addBeer(Beer beer) {
        logger.info("Adding Beer {}" + beer);
        return beerRepository.createBeer(beer);
    }

    @Override
    public void addBeer(String name, double abv, int plato, String style, Quantities quantity, int stock, Containers containers, String brewery, double price){
        logger.info("Adding Beer with name {} from {}", name, brewery);
        Beer beer = new Beer(name, abv, plato, style, quantity, stock, containers, brewery, price);
        beerRepository.createBeer(beer);
    }

    @Override
    public Beer getBeerById(int id) {
        logger.info("Getting Beer with id {}", id);
        return beerRepository.readBeer(id);
    }

    @Override
    public List<Beer> getAllBeers() {
        logger.info("Getting All Beers");
        return beerRepository.readAllBeers();
    }

}
