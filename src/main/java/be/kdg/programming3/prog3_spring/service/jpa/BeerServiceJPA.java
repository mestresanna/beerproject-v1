package be.kdg.programming3.prog3_spring.service.jpa;

import be.kdg.programming3.prog3_spring.Domain.Beer;
import be.kdg.programming3.prog3_spring.Domain.Containers;
import be.kdg.programming3.prog3_spring.Domain.Quantities;
import be.kdg.programming3.prog3_spring.repository.BeerRepository;
import be.kdg.programming3.prog3_spring.repository.jpa.BeerRepositoryJPA;
import be.kdg.programming3.prog3_spring.service.BeerService;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Profile("jpa_rep")
public class BeerServiceJPA implements BeerService {
    private Logger logger = LoggerFactory.getLogger(BeerServiceJPA.class);
    private BeerRepositoryJPA beerRepository;

    public BeerServiceJPA(BeerRepositoryJPA beerRepository) {
        logger.info("Creating Beer Service");
        this.beerRepository = beerRepository;
    }

    @Transactional
    @Override
    public void addBeer(Beer beer) {
        logger.info("Adding Beer {}", beer);
        beerRepository.save(beer);
    }

    @Transactional
    @Override
    public void addBeer(String name, double abv, int plato, String style, Quantities quantity, int stock, Containers containers, String brewery, double price, String imageUrl){
        logger.info("Adding Beer with name {} from {}", name, brewery);
        Beer beer = new Beer(name, abv, plato, style, quantity, stock, containers, brewery, price, imageUrl);
        beerRepository.save(beer);
    }

    @Override
    public Beer getBeerById(int id) {
        logger.debug("Getting Beer with id {}", id);
        return beerRepository.findById(id).orElse(null);
    }

    @Override
    public List<Beer> getAllBeers() {
        logger.debug("Getting All Beers");
        return beerRepository.findAll();
    }
    @Transactional
    @Override
    public void updateBeer(Beer beer){
        beerRepository.save(beer);
    }
    @Transactional
    @Override
    public void delete(int id){
        beerRepository.deleteById(id);
    }

    @Override
    public List<Beer> getNonAlcoholicBeer(){
        double nonAbv = 0.5;
        return beerRepository.findByAbvLessThanEqual(nonAbv);
    }
}
