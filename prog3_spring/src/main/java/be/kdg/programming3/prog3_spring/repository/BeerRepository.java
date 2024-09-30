package be.kdg.programming3.prog3_spring.repository;

import be.kdg.programming3.prog3_spring.Domain.Beer;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BeerRepository {
    private static List<Beer> beers = new ArrayList<>();

    public Beer createBeer(Beer beer) {
        beers.add(beer);
        beer.setIdBeer(beers.size() - 1);
        return beer;
    }

    public Beer readBeer(int idBeer){
        return beers.get(idBeer-1);
    }

    public List<Beer> readAllBeers(){
        return beers;
    }
}
