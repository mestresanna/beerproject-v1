package be.kdg.programming3.prog3_spring.repository.jpa;

import be.kdg.programming3.prog3_spring.Domain.Beer;
import be.kdg.programming3.prog3_spring.Domain.Containers;
import be.kdg.programming3.prog3_spring.Domain.Quantities;
import be.kdg.programming3.prog3_spring.exceptions.DataBaseException;
import be.kdg.programming3.prog3_spring.repository.BeerRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

@Repository
@Profile("jpa")
public class BeerJPARepository implements BeerRepository {
    private Logger logger = LoggerFactory.getLogger(BeerJPARepository.class);

    @PersistenceContext
    private EntityManager em;

    public BeerJPARepository() {}

   @Override
    public Beer findById(int idBeer){
        logger.debug("Reading beer: {}", idBeer);
        Beer beer = em.find(Beer.class, idBeer);
       return beer;
    }

    @Override
    public List<Beer> findAll(){
        logger.debug("Reading all beers");
        List<Beer> beers = em.createQuery("select b from Beer b", Beer.class).getResultList();
        return beers;
    }

    @Override
    public int getSize() {
          return 0;
    }

    @Transactional
    @Override
    public Beer save(Beer beer) {
        logger.info("Creating new beer: {}, with id: {}", beer, beer.getIdBeer());
        switch(beer.getContainers()){
            case CAN ->  beer.setImageUrl("/images/beer-can.png");
            case BOTTLE ->  beer.setImageUrl("/images/beer-bottle.png");
            case KEG ->  beer.setImageUrl("/images/beer-keg.png");
        }
        em.persist(beer);
        return beer;
    }

    @Override
    public int getStock(int idBeer){
        return findById(idBeer).getStock();
    }



    @Override
    @Transactional
    public void updateBeer(Beer beer){
      em.merge(beer);
    }

    @Override
    @Transactional
    public void delete(int id) {
        Beer beer = em.find(Beer.class, id);
        if (beer != null) {
            em.remove(beer);
            logger.debug("Deleted beer with id: {}", id);
        } else {
            logger.warn("Beer with id {} not found.", id);
        }
    }

}
