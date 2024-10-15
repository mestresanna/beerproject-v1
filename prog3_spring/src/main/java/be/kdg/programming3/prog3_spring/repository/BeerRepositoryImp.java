package be.kdg.programming3.prog3_spring.repository;

import be.kdg.programming3.prog3_spring.Domain.Beer;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


public class BeerRepositoryImp implements BeerRepository {
    private static List<Beer> beers = new ArrayList<>();

    @Override
    public Beer createBeer(Beer beer) {
        beers.add(beer);
        beer.setIdBeer(beers.size());
        return beer;
    }

    @Override
    public Beer readBeer(int idBeer){
        return beers.get(idBeer);
    }

    @Override
    public List<Beer> readAllBeers(){
        return beers;
    }

    public int getSize(){
        return beers.size();
    }

    public int getStock(int idBeer){
        return beers.get(idBeer).getStock();
    }
}
