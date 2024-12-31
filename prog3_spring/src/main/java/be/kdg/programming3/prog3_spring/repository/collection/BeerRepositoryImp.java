package be.kdg.programming3.prog3_spring.repository.collection;

import be.kdg.programming3.prog3_spring.Domain.Beer;
import be.kdg.programming3.prog3_spring.repository.BeerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@Profile("collections")
public class BeerRepositoryImp implements BeerRepository {
    private Logger logger = LoggerFactory.getLogger(BeerRepositoryImp.class);
    private static List<Beer> beers = new ArrayList<>();


   @Override
    public Beer findById(int idBeer){
        logger.debug("Reading beer: {}", idBeer);
        return beers.get(idBeer);
    }

    @Override
    public List<Beer> readAllBeers(){
        logger.debug("Reading all beers");
        return beers;
    }

    @Override
    public Beer save(Beer beer) {
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

    public void updateBeer(Beer beer){}

}
