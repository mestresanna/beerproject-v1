package be.kdg.programming3.prog3_spring.repository;

import be.kdg.programming3.prog3_spring.Domain.Beer;
import be.kdg.programming3.prog3_spring.service.BeerServiceImp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


public class BeerRepositoryImp implements BeerRepository {
    private Logger logger = LoggerFactory.getLogger(BeerRepositoryImp.class);
    private static List<Beer> beers = new ArrayList<>();


   @Override
    public Beer readBeer(int idBeer){
        logger.debug("Reading beer: {}", idBeer);
        return beers.get(idBeer);
    }

    @Override
    public List<Beer> readAllBeers(){
        logger.debug("Reading all beers");
        return beers;
    }

    @Override
    public Beer createBeer(Beer beer) {
        beer.setIdBeer(beers.size());
        logger.info("Creating new beer: {}, with id: {}", beer, beer.getIdBeer());
        beers.add(beer);
        return beer;
    }

    public int getSize(){
        return beers.size();
    }

    public int getStock(int idBeer){
        return beers.get(idBeer).getStock();
    }
}
